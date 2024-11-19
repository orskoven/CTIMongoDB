#!/bin/bash

# Directory containing Java files
TARGET_DIR="/Users/simonbeckmann/IdeaProjects/compliMySQL/src/main/java/orsk/compli/service/mongo"

# Check if the target directory exists
if [ ! -d "$TARGET_DIR" ]; then
  echo "Directory $TARGET_DIR does not exist."
  exit 1
fi

# Import statement to be added if missing
IMPORT_STATEMENT="import org.springframework.stereotype.Component;"

# Process each Java file in the target directory
find "$TARGET_DIR" -type f -name "*.java" | while read -r file; do
  # Ensure the import statement is present
  if ! grep -q "$IMPORT_STATEMENT" "$file"; then
    awk -v import="$IMPORT_STATEMENT" 'NR==1{print import}1' "$file" > "${file}.tmp" && mv "${file}.tmp" "$file"
    echo "Added import statement to $file"
  fi

  # Extract the class name using awk
  class_name=$(awk '/public class/{print $3}' "$file")

  if [[ -z "$class_name" ]]; then
    echo "No class definition found in file: $file"
    continue
  fi

  # Prepare the @Component annotation
  component_annotation="@Component(\"mongo${class_name}\")"

  # Check if the @Component annotation is already present
  if grep -q "$component_annotation" "$file"; then
    echo "Annotation already exists in file: $file"
    continue
  fi

  # Insert the annotation before the class declaration
  awk -v annotation="$component_annotation" \
      -v classname="$class_name" \
      '{ if ($0 ~ "public class " classname) print annotation; print $0 }' "$file" > "${file}.tmp" && mv "${file}.tmp" "$file"

  # Notify user of changes
  echo "Added @Component annotation to $file"
done

echo "Script execution completed."
#!/bin/bash

# Define the directory containing entities
entity_dir="/Users/simonbeckmann/IdeaProjects/compli/src/main/java/orsk/compli/entities/mongo"

# Step 1: Retrieve Java class names from entities directory
echo "📂 Scanning for Java class names in the entities directory: $entity_dir"
if [ ! -d "$entity_dir" ]; then
    echo "❌ Error: Directory $entity_dir does not exist."
    exit 1
fi

entity_names=($(find "$entity_dir" -type f -name "*.java" -exec basename {} .java \;))

if [ ${#entity_names[@]} -eq 0 ]; then
    echo "❌ No Java files found in the entities directory."
    exit 1
fi

echo "✅ Found the following entity class names:"
for entity in "${entity_names[@]}"; do
    echo "- $entity"
done
echo

# Step 2: Retrieve directory names in the current directory
echo "📂 Scanning for directories in the current directory..."
directory_names=($(find . -maxdepth 1 -type d -exec basename {} \; | grep -v "^\.$"))

if [ ${#directory_names[@]} -eq 0 ]; then
    echo "❌ No directories found in the current directory."
    exit 1
fi

echo "✅ Found the following directories:"
for dir in "${directory_names[@]}"; do
    echo "- $dir"
done
echo

# Step 3: Match entity names with directory names and correct imports
echo "⚙️ Matching entity names with directories and fixing imports in Java files..."

for entity in "${entity_names[@]}"; do
    for dir in "${directory_names[@]}"; do
        if [[ "$entity" == "$dir" ]]; then
            echo "🔄 Match found: Entity '$entity' matches directory '$dir'"

            # Replace line 5 in relevant Java files with the correct import
            for file in ./*.java; do
                if [ -f "$file" ]; then
                    echo "Updating import in $file..."
                    replacement="import orsk.compli.entities.mongo.$entity; // Corrected by script"
                    sed -i.bak "5s/.*/$replacement/" "$file"
                fi
            done
        fi
    done
done

echo
echo "✅ Import corrections completed. Backups (*.bak) are saved in the same directory."
echo

# Optional: Display updated files
echo "Would you like to display the updated files? (yes/no)"
read -r show_files
if [[ "$show_files" == "yes" ]]; then
    for file in ./*.java; do
        if [ -f "$file" ]; then
            echo "----- $file -----"
            head -n 10 "$file" # Display the first 10 lines for context
            echo
        fi
    done
fi

# Completion message
echo "✨ Task completed! Imports corrected for matched entities."
# Task CLI Application

This is a simple task management application. This program runs on command line interface (CLI)

## Features

- **Adding a task:** Add a new task with a description.
- **Updating a task:** Update the description of an existing task found by task ID.
- **Deleting a task:** Remove an existing task found by task ID.
- **Marking a task:** Mark a task as "in-progress" or "done."
- **Listing tasks:** List all tasks or filter them by status (e.g., `todo`, `in-progress`, `done`).

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/peter-jaesungyu/task-manager
   cd task-cli

2. **Compile the source code:**
    ```bash
   javac -d out src/**/*.java TaskCliApplication.java

3. **Run the application:**
    ```bash
   # If you added this project's directory to PATH
   task-cli <command> [arguments]
   
   # In case you don't want to bother adding PATH
   # for Windows users
   task-cli.bat <command> [arguments]
   # for Linux/Mac users
   ./task-cli.sh <command> [arguments] 
   ```

## Usage
```bash
# Adding a new task
task-cli add "Buy groceries"
# Output: Task added successfully (ID: 1)

# Updating a task
task-cli update 1 "Buy groceries and cook dinner"
# Output: Task updated successfully (ID: 1)

# Deleting a task
task-cli delete 1
# Output: Task deleted successfully (ID: 1)

# Marking a task as in progress
task-cli mark-in-progress 1
# Output: Task's status changed successfully (ID: 1)

# Marking a task as done
task-cli mark-done 1
# Output: Task's status changed successfully (ID: 1)

# Listing all tasks
task-cli list
# Output: List of all tasks

# Listing tasks by status
task-cli list todo
task-cli list in-progress
task-cli list done

```
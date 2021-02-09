Railroad IDE Component List
---

- Display (components for displaying information)
    * Main View port (Container for interacting with the application as a whole)
    * Editor (Text editing space)
    * General tool port
    * Context specific tool port
    * Embedded Window Handler (Any GUI interface that is not meant to be a permanent part of the main display area. Package manager GUI should be a template handled by this)

- Communication
    * GUI Event Bus (facilitates communication between non-gui and gui components)
    * Tool Event Bus (Tool buttons / key combos post an event here for other components to process) 
    * Main Event Bus (Catch all bus for non-specific events)
    * Task Event Bus (Long running task chain events, build/compile events for example)
    
- Tooling
    * Refactoring (receives a tooling event to refactor a thing within a specified context)
    * Importing (For taking a "thing" external to code that can be serialized into code. i.e. a model from the Model editor -> appropriate code space as .json etc...)
    * Package Management (The actual handlers that perform the package management tasks)
    * Model Editor (The exact nature and process of these shouldn't matter so long as they can listen for appropriate tool/gui events and publish appropriate Task/tool events)
    * Texture Editor

- Build
    * PreBuild Component (Everything that needs to happen before actual compilation should be handled by this component, including source validation)
    * Compile Component
    
- Templating
    * Templating handler (JSON based handler that enables de/serialization of GUI and/or Project templates. Should allow the creation of schemas for the different GUI component categories above)

- Plugin
    * WIP - Handle plugins as insertable mixins that can listen and alter to events on specified Buses.
        * An example of what this could look like is a plugin that performs additional formatting on the json files provided via the Task event bus when the Model builder/editor finishes/imports a model.

- Deploy
    * Component for handling deploy tasks (intentionally left vague)

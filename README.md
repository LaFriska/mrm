# mrm
Minecraft Resource Manager STILL UNDER DEVELOPMENT

MRM is a resource manager for Minecraft modding. 
Consider this scenario: a modder wants to create a custom wood type, let's call it redwood. 
Now you need to add in the leaves, the logs, stripped logs etc. Then, you need to add in your custom stairs, slabs, fences, fence gates...
"Ok", says the modder who foolishly attempted modding without MRM, "let's start with redwood stairs". Now, the modder needs to add a JSON file for the blockstate, 
multiple JSON files for the block model depending on its conditions, a JSON file to add it to the stairs tag, a JSON file for its crafting recipe, a JSON file for its
mineable tag, a JSON file for its item model. Repeat this for slabs, fence, fence gates and suddenly, the modder needs to add 100+ JSON files in total for the redwood,
most of which are templated JSON files that could be copied and pasted from existing Minecraft JSONS.

Minecraft Resource Manager abstracts scenarios like this, and allows the modder to add all of that with just a few lines of java code.

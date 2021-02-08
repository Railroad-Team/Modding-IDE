set /p format=Which image format:
set /p argone=What files to start with:
set /p argtwo=What should they be renamed to:

javac *.java>latest.log

java MultipleFilesMultipleColorsExample %format% %argone% %argtwo%>>latest.log
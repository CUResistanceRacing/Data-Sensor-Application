# Data-Sensor-Application

Data Sensor Application - Classes and Details
Sacheth Hegde

GUI

This class runs the graphical user interfaces. All of the visual elements are stored as VisualDisplay objects (these are the graphs, thermometers, etc.)

However, there is a separate bar which essentially acts as a navigator, options, etc. 
This can be opened simply be clicking a jar file, or specifically can be opened with command line arguments specifying certain properties. 

Option Bar Options:

File
Save
Save As
Open
Open Default
Elements
Graph
New
Thermometer
New
Meter
New
   
   -> Etc. for different types of elements

Help
Link to help documentation included

This will be represented at the top via the Mac Menu bar system (at the top), so when the application is opened independently, nothing will be present at first. Then, using the menubar, the user will have a number of options accordingly to open a variety of windows, etc. 

To put all the information in place, the application starts the LayoutManager class, which takes a list of VisualDisplay objects in its constructor and displays them accordingly on the screen.

With regards to the menu bar, saving will save the current configuration (which is all the objects passed into the LayoutManager class) and a list of all their configurations which is present within the VisualDisplay corresponding class. This will include the sensor information, the sensor formatting, etc.

“Open” will open a configuration file, the format of which is to be decided, and is experimental for now, and arrange accordingly on the screen. The configuration file will end with the file type: dsaconfig. 

“Open Default” will automatically open a default to be decided file configuration system. 
After either the file is selected after pressing “Open” or “Open Default” is selected, each of the VisualDisplay objects will be initialized accordingly as specified by the configurations file.

A LayoutManager instance will be called, and passed in an ArrayList of all the different VisualDisplay objects.

FINISH

—————————


LayoutManager

This is in charge of laying out all the windows within the screen. This class’s constructor takes a list of all the VisualDisplay objects and contains a proper position on the screen for each one. For example, it looks at all of the objects and determines what an ideal condition is. An error will be returned accordingly if there is too much stuff present on the screen to be displayed, etc. 

Additionally, the LayoutManager will be in charge of returning a list of all of the JFrame corresponding objects to the GUI class, which will be actually placing them on the screen. The initializing of the Jframe will be done within the LayoutManager, which will be done by calling a common method within the VisualDisplay class.

Abstract/Interface: VisualDisplay

Actually contains everything within one window. Contains the total size, which can be used by LayoutManager to properly arrange everything on the screen properly. Each VisualDisplay will also have to define a class VisualDisplaySettingsWindow which will describe the proper settings, and be present in a separate window (for ex, have a list of information for each of the graphs, etc.).

Elements and Settings`

This is going to be a small window on the side which contains information about the elements currently present in the window, and a place to update the settings of how to read the sensor information, etc. 

InformationRetriever
Retrieves information and stores it accordingly in the a file that can be read by LiveGraph. The tricky part about this is making this so that it properly adjust without having to recode major parts as necessary.

VisualDisplay
Actually contains everything within one window. Contains the total size, which can be used by LayoutManager to properly arrange everything on the screen properly. Each VisualDisplay will also have to define a class VisualDisplaySettingsWindow which will describe the proper settings, and be present in a separate window (for ex, have a list of information for each of the graphs, etc.).

Abstract Class/Interface: VisualDisplayElement: This represents a specific set of information, and belongs to a VisualDisplay class. 

Abstract Class/Interface: VisualDisplaySettingsWindow: Defines a settings window for each VisualDisplay class which can contain information such as stop displaying or recording information for this element. Be sure that if this is the case, to stop unnecessarily storing information for that element!

Graph [Type VisualDisplayElement]:

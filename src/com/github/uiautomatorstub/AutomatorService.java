package com.github.uiautomatorstub;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcError;

public interface AutomatorService {
    /**
     * It's to test if the service is alive.
     * @return 'pong'
     */
	String ping();

	/***************************************************************************
	 * Below section contains all methods from UiDevice.
     ***************************************************************************/

    /**
     * Get the device info.
     * @return device info.
     */
	DeviceInfo deviceInfo();

    /**
     * Perform a click at arbitrary coordinates specified by the user.
     * @param x coordinate
     * @param y coordinate
     * @return true if the click succeeded else false
     */
	boolean click(int x, int y);

    /**
     * Performs a swipe from one coordinate to another coordinate. You can control the smoothness and speed of the swipe by specifying the number of steps. Each step execution is throttled to 5 milliseconds per step, so for a 100 steps, the swipe will take around 0.5 seconds to complete.
     * @param startX X-axis value for the starting coordinate
     * @param startY Y-axis value for the starting coordinate
     * @param endX X-axis value for the ending coordinate
     * @param endY Y-axis value for the ending coordinate
     * @param steps is the number of steps for the swipe action
     * @return true if swipe is performed, false if the operation fails or the coordinates are invalid
     * @throws NotImplementedException
     */
    @JsonRpcErrors({@JsonRpcError(exception=NotImplementedException.class, code=-3)})
	boolean drag(int startX, int startY, int endX, int endY, int steps) throws NotImplementedException;

    /**
     * Performs a swipe from one coordinate to another using the number of steps to determine smoothness and speed. Each step execution is throttled to 5ms per step. So for a 100 steps, the swipe will take about 1/2 second to complete.
     * @param startX X-axis value for the starting coordinate
     * @param startY Y-axis value for the starting coordinate
     * @param endX X-axis value for the ending coordinate
     * @param endY Y-axis value for the ending coordinate
     * @param steps is the number of move steps sent to the system
     * @return false if the operation fails or the coordinates are invalid
     */
	boolean swipe(int startX, int startY, int endX, int endY, int steps);

    /**
     * Helper method used for debugging to dump the current window's layout hierarchy. The file root location is /data/local/tmp
     * @param compressed use compressed layout hierarchy or not using setCompressedLayoutHeirarchy method. Ignore the parameter in case the API level lt 18.
     * @param fileName the filename to be stored.
     */
    void dumpWindowHierarchy(boolean compressed, String fileName);

    /**
     * Take a screenshot of current window and store it as PNG The screenshot is adjusted per screen rotation
     * @param filename where the PNG should be written to
     * @param scale scale the screenshot down if needed; 1.0f for original size
     * @param quality quality of the PNG compression; range: 0-100
     * @return the file name of the screenshot. null if failed.
     * @throws NotImplementedException
     */
    @JsonRpcErrors({@JsonRpcError(exception=NotImplementedException.class, code=-3)})
	String takeScreenshot(String filename, float scale, int quality) throws NotImplementedException;

    /**
     * Disables the sensors and freezes the device rotation at its current rotation state, or enable it.
     * @param freeze true to freeze the rotation, false to unfreeze the rotation.
     * @throws RemoteException
     */
    @JsonRpcErrors({@JsonRpcError(exception=RemoteException.class, code=-1)})
	void freezeRotation(boolean freeze) throws RemoteException;  // freeze or unfreeze rotation, see also unfreezeRotation()

    /**
     * Simulates orienting the device to the left/right/natural and also freezes rotation by disabling the sensors.
     * @param dir Left or l, Right or r, Natural or r, case insensitive
     * @throws RemoteException
     * @throws NotImplementedException
     */
    @JsonRpcErrors({@JsonRpcError(exception=RemoteException.class, code=-1), @JsonRpcError(exception=NotImplementedException.class, code=-3)})
	void setOrientation(String dir) throws RemoteException, NotImplementedException;

    /**
     * Retrieves the text from the last UI traversal event received.
     * @return the text from the last UI traversal event received.
     */
	String getLastTraversedText();

    /**
     * Clears the text from the last UI traversal event.
     */
	void clearLastTraversedText();

    /**
     * Opens the notification shade.
     * @return true if successful, else return false
     * @throws NotImplementedException
     */
    @JsonRpcErrors({@JsonRpcError(exception=NotImplementedException.class, code=-3)})
	boolean openNotification() throws NotImplementedException;

    /**
     * Opens the Quick Settings shade.
     * @return true if successful, else return false
     * @throws NotImplementedException
     */
    @JsonRpcErrors({@JsonRpcError(exception=NotImplementedException.class, code=-3)})
	boolean openQuickSettings() throws NotImplementedException;

    /**
     * Checks if a specific registered UiWatcher has triggered. See registerWatcher(String, UiWatcher). If a UiWatcher runs and its checkForCondition() call returned true, then the UiWatcher is considered triggered. This is helpful if a watcher is detecting errors from ANR or crash dialogs and the test needs to know if a UiWatcher has been triggered.
     * @param watcherName the name of registered watcher.
     * @return true if triggered else false
     */
	boolean hasWatcherTriggered(String watcherName); // We should implement some watchers to treat some blocking issues, e.g. force close dialog

    /**
     * Simulates a short press using key name.
     * @param key possible key name is home, back, left, right, up, down, center, menu, search, enter, delete(or del), recent(recent apps)
     * @return true if successful, else return false
     * @throws RemoteException
     */
    @JsonRpcErrors({@JsonRpcError(exception=RemoteException.class, code=-1)})
	boolean pressKey(String key) throws RemoteException;

    /**
     * Simulates a short press using a key code. See KeyEvent.
     * @param keyCode the key code of the event.
     * @return true if successful, else return false
     */
	boolean pressKeyCode(int keyCode);

    /**
     * Simulates a short press using a key code. See KeyEvent.
     * @param keyCode the key code of the event.
     * @param metaState an integer in which each bit set to 1 represents a pressed meta key
     * @return true if successful, else return false
     */
	boolean pressKeyCode(int keyCode, int metaState);

    /**
     * This method simulates pressing the power button if the screen is OFF else it does nothing if the screen is already ON. If the screen was OFF and it just got turned ON, this method will insert a 500ms delay to allow the device time to wake up and accept input.
     * @throws RemoteException
     */
    @JsonRpcErrors({@JsonRpcError(exception=RemoteException.class, code=-1)})
	void wakeUp() throws RemoteException;

    /**
     * This method simply presses the power button if the screen is ON else it does nothing if the screen is already OFF.
     * @throws RemoteException
     */
    @JsonRpcErrors({@JsonRpcError(exception=RemoteException.class, code=-1)})
	void sleep() throws RemoteException;

    /**
     * Checks the power manager if the screen is ON.
     * @return true if the screen is ON else false
     * @throws RemoteException
     */
    @JsonRpcErrors({@JsonRpcError(exception=RemoteException.class, code=-1)})
	boolean isScreenOn() throws RemoteException;

    /**
     * Waits for the current application to idle.
     * @param timeout in milliseconds
     */
	void waitForIdle(long timeout);

    /**
     * Waits for a window content update event to occur. If a package name for the window is specified, but the current window does not have the same package name, the function returns immediately.
     * @param packageName the specified window package name (can be null). If null, a window update from any front-end window will end the wait.
     * @param timeout the timeout for the wait
     * @return true if a window update occurred, false if timeout has elapsed or if the current window does not have the specified package name
     */
	boolean waitForWindowUpdate (String packageName, long timeout);

	/***************************************************************************
	 * Below section contains all methods from UiObject.
     ***************************************************************************/

    /**
     * Clears the existing text contents in an editable field. The UiSelector of this object must reference a UI element that is editable. When you call this method, the method first sets focus at the start edge of the field. The method then simulates a long-press to select the existing text, and deletes the selected text. If a "Select-All" option is displayed, the method will automatically attempt to use it to ensure full text selection. Note that it is possible that not all the text in the field is selected; for example, if the text contains separators such as spaces, slashes, at symbol etc. Also, not all editable fields support the long-press functionality.
     * @param obj the selector of the UiObject.
     * @throws UiObjectNotFoundException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
	void clearTextField(Selector obj) throws UiObjectNotFoundException;

    /**
     * Reads the text property of the UI element
     * @param obj the selector of the UiObject.
     * @return text value of the current node represented by this UiObject
     * @throws UiObjectNotFoundException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
    String getText(Selector obj) throws UiObjectNotFoundException;

    /**
     * Sets the text in an editable field, after clearing the field's content. The UiSelector selector of this object must reference a UI element that is editable. When you call this method, the method first simulates a click() on editable field to set focus. The method then clears the field's contents and injects your specified text into the field. If you want to capture the original contents of the field, call getText() first. You can then modify the text and use this method to update the field.
     * @param obj the selector of the UiObject.
     * @param text string to set
     * @return true if operation is successful
     * @throws UiObjectNotFoundException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
    String setText(Selector obj, String text) throws UiObjectNotFoundException;

	/**
	 * Performs a click at the center of the visible bounds of the UI element represented by this UiObject.
	 * @param obj the target ui object.
	 * @return true id successful else false
	 * @throws UiObjectNotFoundException
	 */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
	boolean click(Selector obj) throws UiObjectNotFoundException;

    /**
	 * Clicks the bottom and right corner or top and left corner of the UI element
	 * @param obj the target ui object.
	 * @param corner "br"/"bottomright" means BottomRight, "tl"/"topleft" means TopLeft, "center" means Center.
	 * @return true on success
	 * @throws UiObjectNotFoundException
	 */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
	boolean click(Selector obj, String corner) throws UiObjectNotFoundException;

    /**
     * Performs a click at the center of the visible bounds of the UI element represented by this UiObject and waits for window transitions. This method differ from click() only in that this method waits for a a new window transition as a result of the click. Some examples of a window transition:
     * - launching a new activity
     * - bringing up a pop-up menu
     * - bringing up a dialog
     * @param obj the target ui object.
     * @param timeout timeout before giving up on waiting for a new window
     * @return true if the event was triggered, else false
     * @throws UiObjectNotFoundException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
	boolean clickAndWaitForNewWindow(Selector obj, long timeout) throws UiObjectNotFoundException;

    /**
     * Long clicks the center of the visible bounds of the UI element
     * @param obj the target ui object.
     * @return true if operation was successful
     * @throws UiObjectNotFoundException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
	boolean longClick(Selector obj) throws UiObjectNotFoundException;

    /**
     * Long clicks bottom and right corner of the UI element
     * @param obj the target ui object.
     * @param corner "br"/"bottomright" means BottomRight, "tl"/"topleft" means TopLeft, "center" means Center.
     * @return true if operation was successful
     * @throws UiObjectNotFoundException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
	boolean longClick(Selector obj, String corner) throws UiObjectNotFoundException;

    /**
     * Drags this object to a destination UiObject. The number of steps specified in your input parameter can influence the drag speed, and varying speeds may impact the results. Consider evaluating different speeds when using this method in your tests.
     * @param obj the ui object to be dragged.
     * @param destObj the ui object to be dragged to.
     * @param steps usually 40 steps. You can increase or decrease the steps to change the speed.
     * @return true if successful
     * @throws UiObjectNotFoundException
     * @throws NotImplementedException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2), @JsonRpcError(exception=NotImplementedException.class, code=-3)})
	boolean dragTo (Selector obj, Selector destObj, int steps) throws UiObjectNotFoundException, NotImplementedException;

    /**
     * Drags this object to arbitrary coordinates. The number of steps specified in your input parameter can influence the drag speed, and varying speeds may impact the results. Consider evaluating different speeds when using this method in your tests.
     * @param obj the ui object to be dragged.
     * @param destX the X-axis coordinate of destination.
     * @param destY the Y-axis coordinate of destination.
     * @param steps usually 40 steps. You can increase or decrease the steps to change the speed.
     * @return true if successful
     * @throws UiObjectNotFoundException
     * @throws NotImplementedException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2), @JsonRpcError(exception=NotImplementedException.class, code=-3)})
	boolean dragTo (Selector obj, int destX, int destY, int steps) throws UiObjectNotFoundException, NotImplementedException;

    /**
     * Check if view exists. This methods performs a waitForExists(long) with zero timeout. This basically returns immediately whether the view represented by this UiObject exists or not.
     * @param obj the ui object to be dragged.
     * @return true if the view represented by this UiObject does exist
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
	boolean exist(Selector obj);

    /**
     * Get the object info.
     * @param obj the target ui object.
     * @return object info.
     * @throws UiObjectNotFoundException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
	ObjInfo objInfo(Selector obj) throws UiObjectNotFoundException;

    /**
     * Generates a two-pointer gesture with arbitrary starting and ending points.
     * @param obj the target ui object. ??
     * @param startPoint1	start point of pointer 1
     * @param startPoint2	start point of pointer 2
     * @param endPoint1	end point of pointer 1
     * @param endPoint2	end point of pointer 2
     * @param steps	the number of steps for the gesture. Steps are injected about 5 milliseconds apart, so 100 steps may take around 0.5 seconds to complete.
     * @return true if all touch events for this gesture are injected successfully, false otherwise
     * @throws UiObjectNotFoundException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2), @JsonRpcError(exception=NotImplementedException.class, code=-3)})
    boolean gesture(Selector obj, Point startPoint1, Point startPoint2, Point endPoint1, Point endPoint2, int steps) throws UiObjectNotFoundException, NotImplementedException;

    /**
     * Performs a two-pointer gesture, where each pointer moves diagonally toward the other, from the edges to the center of this UiObject .
     * @param obj the target ui object.
     * @param percent percentage of the object's diagonal length for the pinch gesture
     * @param steps the number of steps for the gesture. Steps are injected about 5 milliseconds apart, so 100 steps may take around 0.5 seconds to complete.
     * @return true if all touch events for this gesture are injected successfully, false otherwise
     * @throws UiObjectNotFoundException
     * @throws NotImplementedException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2), @JsonRpcError(exception=NotImplementedException.class, code=-3)})
	boolean pinchIn(Selector obj, int percent, int steps) throws UiObjectNotFoundException, NotImplementedException;

    /**
     * Performs a two-pointer gesture, where each pointer moves diagonally opposite across the other, from the center out towards the edges of the this UiObject.
     * @param obj the target ui object.
     * @param percent percentage of the object's diagonal length for the pinch gesture
     * @param steps the number of steps for the gesture. Steps are injected about 5 milliseconds apart, so 100 steps may take around 0.5 seconds to complete.
     * @return true if all touch events for this gesture are injected successfully, false otherwise
     * @throws UiObjectNotFoundException
     * @throws NotImplementedException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2), @JsonRpcError(exception=NotImplementedException.class, code=-3)})
	boolean pinchOut(Selector obj, int percent, int steps) throws UiObjectNotFoundException, NotImplementedException;

    /**
	 * Performs the swipe up/down/left/right action on the UiObject
	 * @param obj the target ui object.
	 * @param dir "u"/"up", "d"/"down", "l"/"left", "r"/"right"
	 * @param steps indicates the number of injected move steps into the system. Steps are injected about 5ms apart. So a 100 steps may take about 1/2 second to complete.
	 * @return true of successful
	 * @throws UiObjectNotFoundException
	 */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
	boolean swipe(Selector obj, String dir, int steps) throws UiObjectNotFoundException;

    /**
     * Waits a specified length of time for a view to become visible. This method waits until the view becomes visible on the display, or until the timeout has elapsed. You can use this method in situations where the content that you want to select is not immediately displayed.
     * @param obj the target ui object
     * @param timeout time to wait (in milliseconds)
     * @return true if the view is displayed, else false if timeout elapsed while waiting
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
	boolean waitForExists (Selector obj, long timeout);

    /**
     * Waits a specified length of time for a view to become undetectable. This method waits until a view is no longer matchable, or until the timeout has elapsed. A view becomes undetectable when the UiSelector of the object is unable to find a match because the element has either changed its state or is no longer displayed. You can use this method when attempting to wait for some long operation to compete, such as downloading a large file or connecting to a remote server.
     * @param obj the target ui object
     * @param timeout time to wait (in milliseconds)
     * @return true if the element is gone before timeout elapsed, else false if timeout elapsed but a matching element is still found.
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
	boolean waitUntilGone (Selector obj, long timeout);

    /***************************************************************************
     * Below section contains all methods from UiScrollable.
     ***************************************************************************/

    /**
     * Performs a backwards fling action with the default number of fling steps (5). If the swipe direction is set to vertical, then the swipe will be performed from top to bottom. If the swipe direction is set to horizontal, then the swipes will be performed from left to right. Make sure to take into account devices configured with right-to-left languages like Arabic and Hebrew.
     * @param obj the selector of the scrollable object
     * @param isVertical vertical or horizontal
     * @return true if scrolled, and false if can't scroll anymore
     * @throws UiObjectNotFoundException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
    boolean flingBackward(Selector obj, boolean isVertical) throws UiObjectNotFoundException;

    /**
     * Performs a forward fling with the default number of fling steps (5). If the swipe direction is set to vertical, then the swipes will be performed from bottom to top. If the swipe direction is set to horizontal, then the swipes will be performed from right to left. Make sure to take into account devices configured with right-to-left languages like Arabic and Hebrew.
     * @param obj the selector of the scrollable object
     * @param isVertical vertical or horizontal
     * @return true if scrolled, and false if can't scroll anymore
     * @throws UiObjectNotFoundException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
    boolean flingForward(Selector obj, boolean isVertical) throws UiObjectNotFoundException;

    /**
     * Performs a fling gesture to reach the beginning of a scrollable layout element. The beginning can be at the top-most edge in the case of vertical controls, or the left-most edge for horizontal controls. Make sure to take into account devices configured with right-to-left languages like Arabic and Hebrew.
     * @param obj the selector of the scrollable object
     * @param isVertical vertical or horizontal
     * @param maxSwipes max swipes to achieve beginning.
     * @return true on scrolled, else false
     * @throws UiObjectNotFoundException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
    boolean flingToBeginning (Selector obj, boolean isVertical, int maxSwipes) throws UiObjectNotFoundException;

    /**
     * Performs a fling gesture to reach the end of a scrollable layout element. The end can be at the bottom-most edge in the case of vertical controls, or the right-most edge for horizontal controls. Make sure to take into account devices configured with right-to-left languages like Arabic and Hebrew.
     * @param obj the selector of the scrollable object
     * @param isVertical vertical or horizontal
     * @param maxSwipes max swipes to achieve end.
     * @return true on scrolled, else false
     * @throws UiObjectNotFoundException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
    boolean flingToEnd (Selector obj, boolean isVertical, int maxSwipes) throws UiObjectNotFoundException;

    /**
     * Performs a backward scroll. If the swipe direction is set to vertical, then the swipes will be performed from top to bottom. If the swipe direction is set to horizontal, then the swipes will be performed from left to right. Make sure to take into account devices configured with right-to-left languages like Arabic and Hebrew.
     * @param obj the selector of the scrollable object
     * @param isVertical vertical or horizontal
     * @param steps number of steps. Use this to control the speed of the scroll action.
     * @return true if scrolled, false if can't scroll anymore
     * @throws UiObjectNotFoundException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
    boolean scrollBackward (Selector obj, boolean isVertical, int steps) throws UiObjectNotFoundException;

    /**
     * Performs a forward scroll with the default number of scroll steps (55). If the swipe direction is set to vertical, then the swipes will be performed from bottom to top. If the swipe direction is set to horizontal, then the swipes will be performed from right to left. Make sure to take into account devices configured with right-to-left languages like Arabic and Hebrew.
     * @param obj the selector of the scrollable object
     * @param isVertical vertical or horizontal
     * @param steps number of steps. Use this to control the speed of the scroll action.
     * @return true on scrolled, else false
     * @throws UiObjectNotFoundException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
    boolean scrollForward (Selector obj, boolean isVertical, int steps) throws UiObjectNotFoundException;

    /**
     * Scrolls to the beginning of a scrollable layout element. The beginning can be at the top-most edge in the case of vertical controls, or the left-most edge for horizontal controls. Make sure to take into account devices configured with right-to-left languages like Arabic and Hebrew.
     * @param obj the selector of the scrollable object
     * @param isVertical vertical or horizontal
     * @param maxSwipes max swipes to be performed.
     * @param steps use steps to control the speed, so that it may be a scroll, or fling
     * @return true on scrolled else false
     * @throws UiObjectNotFoundException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
    boolean scrollToBeginning (Selector obj, boolean isVertical, int maxSwipes, int steps) throws UiObjectNotFoundException;

    /**
     * Scrolls to the end of a scrollable layout element. The end can be at the bottom-most edge in the case of vertical controls, or the right-most edge for horizontal controls. Make sure to take into account devices configured with right-to-left languages like Arabic and Hebrew.
     * @param obj the selector of the scrollable object
     * @param isVertical vertical or horizontal
     * @param maxSwipes max swipes to be performed.
     * @param steps use steps to control the speed, so that it may be a scroll, or fling
     * @return true on scrolled, else false
     * @throws UiObjectNotFoundException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
    boolean scrollToEnd (Selector obj, boolean isVertical, int maxSwipes, int steps) throws UiObjectNotFoundException;

    /**
     * Perform a scroll forward action to move through the scrollable layout element until a visible item that matches the selector is found.
     * @param obj the selector of the scrollable object
     * @param targetObj the item matches the selector to be found.
     * @param isVertical vertical or horizontal
     * @param maxSwipes max swipes to be performed.
     * @param steps use steps to control the speed, so that it may be a scroll, or fling
     * @return true on scrolled, else false
     * @throws UiObjectNotFoundException
     */
    @JsonRpcErrors({@JsonRpcError(exception=UiObjectNotFoundException.class, code=-2)})
    boolean scrollTo (Selector obj, Selector targetObj, boolean isVertical, int maxSwipes, int steps) throws UiObjectNotFoundException;
}

package io.github.railroad.layout;

import java.io.File;

import io.github.railroad.config.LanguageConfig;
import io.github.railroad.objects.RailroadSplitPane;
import io.github.railroad.objects.RailroadTabLabel;
import io.github.railroad.objects.RailroadTabPane;
import io.github.railroad.objects.RailroadTextEditor;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class LayoutManager {

	private final Pane primaryNode;
	private LanguageConfig langConfig;
	private static final File TEST_LAYOUT = new File("/assets/test_layout/test.layout");
	private ObjectProperty<Tab> draggingTab;

	public LayoutManager(LanguageConfig langConfig, Pane primaryNode) {
		this.langConfig = langConfig;
		this.primaryNode = primaryNode;
		this.draggingTab = new SimpleObjectProperty<>();

		Tab testTab = new Tab();
		final RailroadTabLabel testLabel = new RailroadTabLabel(testTab, "test");
		testTab.setGraphic(testLabel);
		testTab.setContent(new RailroadTextEditor());
		RailroadTabPane firstTabPane = new RailroadTabPane(this.primaryNode, testTab);
		addToPane(this.primaryNode, firstTabPane);
		addTabbedPane(firstTabPane, new RailroadTabPane(this.primaryNode));
	}

	/**
	 * Attempts to add a tabbed pane to index
	 * 
	 * @param selectedPane the pane that is currently selected m
	 * 
	 * @return The new tab pane.
	 *
	 */
	public static void addTabbedPane(RailroadTabPane selectedPane, RailroadTabPane sourcePane) {
		// get parent of selectedpane
		// add SplitPane to parent
		// add selectedPane to parent
		// add new tabbed Pane to parent
		RailroadSplitPane newSplit = new RailroadSplitPane();
		newSplit.getItems().addAll(selectedPane, sourcePane);
		Region parent = (Region) selectedPane.getRealParent();
		addToPane(parent, newSplit);
		removeFromPane(parent, selectedPane);
	}

	public static void addToPane(Region pane, Node node) {
		if (pane instanceof VBox) {
			((VBox) pane).getChildren().add(node);
		} else if (pane instanceof RailroadSplitPane) {
			((RailroadSplitPane) pane).getItems().add(node);
		}
	}

	public static void removeFromPane(Region pane, Node node) {
		if (pane instanceof VBox) {
			((VBox) pane).getChildren().remove(node);
		} else if (pane instanceof RailroadSplitPane) {
			((RailroadSplitPane) pane).getItems().remove(node);
		}
	}

	public boolean removeTabbedPane(Pane pane) {
		// This will be the opposite of addTabbedPane basically
		return true;
	}

	/**
	 * Opens a choose file dialog for the layout.
	 * 
	 * @return The layout file that the user picked.
	 */
	public File openLoadLayoutDialog() throws SecurityException, NullPointerException, IllegalArgumentException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(this.langConfig.get("layoutmanager.load_file"));
		String desktopPath = System.getProperty("user.home") + "/Desktop";
		fileChooser.setInitialDirectory(new File(desktopPath));
		fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Layout", new String[] { "*.layout" }));
		File layoutFile = fileChooser.showOpenDialog(null);
		return layoutFile;
	}

	public boolean load(File file) {
		if (file == null)
			return false;
		return true;
	}

	public boolean save() {
		for (Node child : this.primaryNode.getChildren()) {
			if (child instanceof Saveable)
				((Saveable) child).saveData();
		}
		return true;
	}
}

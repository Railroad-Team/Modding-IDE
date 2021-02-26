package io.github.railroad.layout;

import java.io.File;

import io.github.railroad.config.LanguageConfig;
import io.github.railroad.objects.RailroadSplitPane;
import io.github.railroad.objects.RailroadTabLabel;
import io.github.railroad.objects.RailroadTabPane;
import io.github.railroad.objects.RailroadTextEditor;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

//REMOVE BEFORE YOU ADD LMAO
public class LayoutManager { // AKA THE PAIN TRAIN

	private final Pane primaryNode;
	private LanguageConfig langConfig;
	private static final File TEST_LAYOUT = new File("/assets/test_layout/test.layout");

	public LayoutManager(LanguageConfig langConfig, Pane primaryNode) {
		this.langConfig = langConfig;
		this.primaryNode = primaryNode;

		Tab testTab = new Tab();
		Tab testTab2 = new Tab();
		Tab testTab3 = new Tab();
		final RailroadTabLabel testLabel = new RailroadTabLabel(testTab, "debug");
		final RailroadTabLabel testLabel2 = new RailroadTabLabel(testTab2, "explorer");
		final RailroadTabLabel testLabel3 = new RailroadTabLabel(testTab3, "editor");
		testTab.setGraphic(testLabel);
		testTab2.setGraphic(testLabel2);
		testTab3.setGraphic(testLabel3);
		testTab.setContent(new RailroadTextEditor());
		RailroadTabPane firstTabPane = new RailroadTabPane(this.primaryNode, testTab, testTab2, testTab3);
		addToPane(this.primaryNode, firstTabPane);
	}

	/**
	 * Attempts to add a tabbed pane to index
	 * 
	 * @param selectedPane the pane that is currently selected m
	 * 
	 * @return The new tab pane.
	 *
	 */
	public static RailroadTabPane splitTabPane(RailroadTabPane destinationPane, Tab sourceTab, Orientation orientation) {
		// masterParent
		Node parent = destinationPane.getRealParent();

		// new child replacing the destination (imposter child)
		RailroadSplitPane newSplitChild = new RailroadSplitPane();
		newSplitChild.setOrientation(orientation);

		TabPane sourceTabPane = sourceTab.getTabPane();

		// sourceTab newborn abducted from the sourceTabPane
		sourceTabPane.getTabs().remove(sourceTab);

		// destinationPane(original child) becomes child of the new split child
		destinationPane.setRealParent(newSplitChild);

		// puppet parent becomes the bag that the imposter uses to abduct the source tab
		RailroadTabPane newTabPane = new RailroadTabPane(newSplitChild, sourceTab);

		// destinationPane is removed from the master parent
		removeFromPane(parent, destinationPane);

		// destinationPane and the bag(newTabPane) become children of the imposter
		newSplitChild.getItems().addAll(destinationPane, newTabPane);

		// imposter becomes child of master parent
		addToPane(parent, newSplitChild);
		newSplitChild.setRealParent(parent);

		if (sourceTabPane instanceof RailroadTabPane) {
			removeTabPane((RailroadTabPane) sourceTabPane);
		}

		return newTabPane;
	}

	public static void removeTabPane(RailroadTabPane sourceTabPane) {
		// when moving 1 tab in smaller split into other side of smaller split,
		// everything dies(fatal heart attack)
		if (sourceTabPane.getTabs().isEmpty()) {
			Node parent = sourceTabPane.getRealParent();
			if (parent instanceof RailroadSplitPane) {
				// splitParent == imposter
				RailroadSplitPane splitParent = ((RailroadSplitPane) parent);
				for (Node child : splitParent.getItems()) {
					if (child != sourceTabPane) {
						splitParent.getItems().clear();
						Node masterParent = splitParent.getRealParent();
						removeFromPane(masterParent, splitParent);
						addToPane(masterParent, child);
						if (child instanceof RailroadTabPane) {
							((RailroadTabPane) child).setRealParent(masterParent);
						}
					}
				}
			}
		}
	}

	public static void addToPane(Node parent, Node node) {
		if (parent instanceof VBox) {
			((VBox) parent).getChildren().add(node);
		} else if (parent instanceof RailroadSplitPane) {
			((RailroadSplitPane) parent).getItems().add(node);
		} else {
			System.out.println("Somehow not VBox or RailroadSplitPane when adding!");
		}
	}

	public static void removeFromPane(Node parent, Node node) {
		if (parent instanceof VBox) {
			((VBox) parent).getChildren().remove(node);
		} else if (parent instanceof RailroadSplitPane) {
			((RailroadSplitPane) parent).getItems().remove(node);
		} else {
			System.out.println("Somehow not VBox or RailroadSplitPane when removing!");
		}
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

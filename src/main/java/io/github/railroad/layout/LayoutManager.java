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

		var testTab = new Tab();
		var testTab2 = new Tab();
		var testTab3 = new Tab();
		final var testLabel = new RailroadTabLabel(testTab, "debug");
		final var testLabel2 = new RailroadTabLabel(testTab2, "explorer");
		final var testLabel3 = new RailroadTabLabel(testTab3, "editor");
		testTab.setGraphic(testLabel);
		testTab2.setGraphic(testLabel2);
		testTab3.setGraphic(testLabel3);
		testTab.setContent(new RailroadTextEditor());
		var firstTabPane = new RailroadTabPane(this.primaryNode, testTab, testTab2, testTab3);
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
		var newSplitChild = new RailroadSplitPane();
		newSplitChild.setOrientation(orientation);

		var sourceTabPane = sourceTab.getTabPane();

		// sourceTab newborn abducted from the sourceTabPane
		sourceTabPane.getTabs().remove(sourceTab);

		// destinationPane(original child) becomes child of the new split child
		destinationPane.setRealParent(newSplitChild);

		// puppet parent becomes the bag that the imposter uses to abduct the source tab
		var newTabPane = new RailroadTabPane(newSplitChild, sourceTab);

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
			//TODO: Issue is here, not adding at correct index
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
		var fileChooser = new FileChooser();
		fileChooser.setTitle(this.langConfig.get("layoutmanager.load_file"));
		String desktopPath = System.getProperty("user.home") + "/Desktop";
		fileChooser.setInitialDirectory(new File(desktopPath));
		fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Layout", "*.layout"));
		return fileChooser.showOpenDialog(null);
	}

	public boolean load(File file) {
		if (file == null)
			return false;
		// TODO
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

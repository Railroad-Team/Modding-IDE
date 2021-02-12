package io.github.railroad.layout;

public interface Saveable {

	default void saveData() {
		System.out.println(this.getClass().getSimpleName() + " had their data saved!");
	}

	default void loadData() {
		System.out.println(this.getClass().getSimpleName() + " had their data loaded!");
	}
}

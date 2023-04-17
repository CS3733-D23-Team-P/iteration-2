package edu.wpi.punchy_pegasi.frontend.components;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class PFXDropdown<T> extends ComboBox<T> {
    ObservableList<T> items;
    FilteredList<T> filteredItems;
    BiPredicate<? super T, String> predicate;

    public PFXDropdown(ObservableList<T> items, BiPredicate<? super T, String> predicate) {
        super();
        setEditable(true);
        filteredItems = new FilteredList<>(items, p -> true);
        this.items = items;
        this.predicate = predicate;
        getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            final TextField editor = getEditor();
            final T selected = getSelectionModel().getSelectedItem();
            Platform.runLater(() -> {
                if (selected == null || !selected.equals(editor.getText())) {
                    filteredItems.setPredicate(i -> predicate.test(i, newValue));
                }
            });
        });
        setItems(filteredItems);
    }
}
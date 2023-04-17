package edu.wpi.punchy_pegasi.frontend.components;

import org.controlsfx.control.SearchableComboBox;

public class PFXDropdown<T> extends SearchableComboBox<T> {

    public PFXDropdown() {
        super();
        this.getStyleClass().add("pfx-dropdown");
    }

}

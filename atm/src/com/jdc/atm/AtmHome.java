package com.jdc.atm;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;

public class AtmHome implements Initializable {

	private static final String atm = "M28 15.5v-13c0-0.266-0.234-0.5-0.5-0.5h-25c-0.266 0-0.5 0.234-0.5 0.5v13c0 0.266 0.234 0.5 0.5 0.5h25c0.266 0 0.5-0.234 0.5-0.5zM30 2.5v17c0 1.375-1.125 2.5-2.5 2.5h-8.5c0 1.328 1 2.453 1 3s-0.453 1-1 1h-8c-0.547 0-1-0.453-1-1 0-0.578 1-1.641 1-3h-8.5c-1.375 0-2.5-1.125-2.5-2.5v-17c0-1.375 1.125-2.5 2.5-2.5h25c1.375 0 2.5 1.125 2.5 2.5z";
	private static final String setting = "M16 14c0-2.203-1.797-4-4-4s-4 1.797-4 4 1.797 4 4 4 4-1.797 4-4zM24 12.297v3.469c0 0.234-0.187 0.516-0.438 0.562l-2.891 0.438c-0.172 0.5-0.359 0.969-0.609 1.422 0.531 0.766 1.094 1.453 1.672 2.156 0.094 0.109 0.156 0.25 0.156 0.391s-0.047 0.25-0.141 0.359c-0.375 0.5-2.484 2.797-3.016 2.797-0.141 0-0.281-0.063-0.406-0.141l-2.156-1.687c-0.453 0.234-0.938 0.438-1.422 0.594-0.109 0.953-0.203 1.969-0.453 2.906-0.063 0.25-0.281 0.438-0.562 0.438h-3.469c-0.281 0-0.531-0.203-0.562-0.469l-0.438-2.875c-0.484-0.156-0.953-0.344-1.406-0.578l-2.203 1.672c-0.109 0.094-0.25 0.141-0.391 0.141s-0.281-0.063-0.391-0.172c-0.828-0.75-1.922-1.719-2.578-2.625-0.078-0.109-0.109-0.234-0.109-0.359 0-0.141 0.047-0.25 0.125-0.359 0.531-0.719 1.109-1.406 1.641-2.141-0.266-0.5-0.484-1.016-0.641-1.547l-2.859-0.422c-0.266-0.047-0.453-0.297-0.453-0.562v-3.469c0-0.234 0.187-0.516 0.422-0.562l2.906-0.438c0.156-0.5 0.359-0.969 0.609-1.437-0.531-0.75-1.094-1.453-1.672-2.156-0.094-0.109-0.156-0.234-0.156-0.375s0.063-0.25 0.141-0.359c0.375-0.516 2.484-2.797 3.016-2.797 0.141 0 0.281 0.063 0.406 0.156l2.156 1.672c0.453-0.234 0.938-0.438 1.422-0.594 0.109-0.953 0.203-1.969 0.453-2.906 0.063-0.25 0.281-0.438 0.562-0.438h3.469c0.281 0 0.531 0.203 0.562 0.469l0.438 2.875c0.484 0.156 0.953 0.344 1.406 0.578l2.219-1.672c0.094-0.094 0.234-0.141 0.375-0.141s0.281 0.063 0.391 0.156c0.828 0.766 1.922 1.734 2.578 2.656 0.078 0.094 0.109 0.219 0.109 0.344 0 0.141-0.047 0.25-0.125 0.359-0.531 0.719-1.109 1.406-1.641 2.141 0.266 0.5 0.484 1.016 0.641 1.531l2.859 0.438c0.266 0.047 0.453 0.297 0.453 0.562z";

	@FXML
	private SVGPath icon;

	@FXML
	private StackPane content;

	@FXML
	void switchView(MouseEvent event) {

		try {
			Parent view = null;
			if (icon.getId().equals("setting")) {
				icon.setId("atm");
				icon.setContent(atm);
				view = AtmSetting.getView();
			} else {
				icon.setContent(setting);
				icon.setId("setting");
				view = AtmView.getView();
			}

			content.getChildren().clear();
			content.getChildren().add(view);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			icon.setContent(setting);
			icon.setId("setting");
			content.getChildren().add(AtmView.getView());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

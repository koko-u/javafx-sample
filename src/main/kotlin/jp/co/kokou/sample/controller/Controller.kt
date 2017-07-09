package jp.co.kokou.sample.controller

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField

class Controller {

    @FXML
    lateinit var messageLabel: Label
    @FXML
    lateinit var inputField: TextField
    @FXML
    lateinit var okButton: Button

    @FXML
    fun clickOk() {
        messageLabel.text = "あなたは「${inputField.text}」と書いた"
    }
}
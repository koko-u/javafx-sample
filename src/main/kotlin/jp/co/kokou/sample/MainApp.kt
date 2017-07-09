package jp.co.kokou.sample

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

fun main(args: Array<String>) {
    Application.launch(MainApp::class.java, *args)
}

class MainApp : Application() {
    override fun start(primaryStage: Stage) {
        primaryStage.title = "Hello World"
        primaryStage.scene = Scene(FXMLLoader.load<Parent>(this.javaClass.getResource("/fxml/main.fxml")), 300.0, 275.0)
        primaryStage.show()
    }
}
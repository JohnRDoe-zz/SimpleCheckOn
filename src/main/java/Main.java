import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {


    private Button mOpenButton;
    private FileChooser mFileChooser;


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(final Stage stage) {
        stage.setTitle("File Chooser Sample");
        Pane rootGroup = initView(stage);
        stage.setScene(new Scene(rootGroup));
        stage.show();
    }


    private Pane initView(Stage stage) {
        mOpenButton = new Button("选择考勤文件");
        mFileChooser = configureFileChooser();
        mOpenButton.setOnAction(
                (final ActionEvent e) -> {
                    File file = mFileChooser.showOpenDialog(stage);
                    if (file != null) {
                        WorkService.readXls(file);//读取表格文件
                    }
                });


        GridPane inputGridPane = new GridPane();
        GridPane.setConstraints(mOpenButton, 0, 0);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(mOpenButton);
        Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));
        return rootGroup;
    }


    /**
     * 设置文件过滤器
     */
    private static FileChooser configureFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("xls", "*.xls"),
                new FileChooser.ExtensionFilter("xlsx", "*.xlsx")
        );
        return fileChooser;
    }

}

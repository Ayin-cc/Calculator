import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculator extends Application {
    private boolean isInputDouble = false;
    private boolean isInputed = false;

    @Override
    public void start(Stage primaryStage) {
        // 新建窗体
        primaryStage.setTitle("计算器");
        primaryStage.setResizable(false);

        // 网格布局
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(15, 15, 15, 15));

        // 显示框
        TextField tempDisplay = new TextField();
        TextField optDisplay = new TextField();
        TextField display = new TextField();
        tempDisplay.setEditable(false);
        tempDisplay.setStyle("-fx-text-fill: gray;");
        optDisplay.setEditable(false);
        optDisplay.setStyle("-fx-text-fill: gray;");
        display.setEditable(false);
        display.setText("0");
        grid.add(tempDisplay, 0, 0, 3, 1);
        grid.add(optDisplay, 3, 0, 1, 1);
        grid.add(display, 0, 1, 4, 1);

        // 添加按钮
        String[] buttonLabels = {
                "%", "²√", "^", "+",
                "7", "8", "9", "-",
                "4", "5", "6", "×",
                "1", "2", "3", "÷",
                "C", "0", ".", "=",
        };

        for (int i = 0; i < 20; i++) {
            Button button = new Button(buttonLabels[i]);
            button.setMinSize(50, 50);
            button.setOnAction(event -> handleButtonClick(button.getText(), tempDisplay, optDisplay, display));
            grid.add(button, i % 4, i / 4 + 2);
        }

        // 显示
        Scene scene = new Scene(grid, 265, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // 按钮的 handler
    private void handleButtonClick(String value, TextField tempDisplay, TextField optDisplay, TextField display) {
        if (value.equals("C")) {
            // 归零
            isInputDouble = false;
            isInputed = false;
            tempDisplay.clear();
            optDisplay.clear();
            display.setText("0");
        }
        else if (value.equals("=")) {
            // 显示运算结果
            try {
                String result = evaluateExpression(Double.parseDouble(tempDisplay.getText()), Double.parseDouble(display.getText()), optDisplay.getText());
                display.setText(result);
            } catch (Exception e) {
                display.setText("Error");
            }
            isInputDouble = false;
            isInputed = true;
            tempDisplay.clear();
            optDisplay.clear();
        }
        else if(value.equals(".")){
            // 小数
            if(isInputDouble){
                return;
            }
            isInputDouble = true;
            isInputed = true;
            display.appendText(value);
        }
        else if(value.equals("+") || value.equals("-") || value.equals("×") || value.equals("÷") || value.equals("%") || value.equals("^")){
            // 运算符
            tempDisplay.setText(display.getText());
            optDisplay.setText(value);
            display.setText("0");
            isInputed = false;
            isInputDouble = false;
        }
        else if(value.equals("²√")){
            // 平方根
            try {
                double res = Double.parseDouble(display.getText());
                res = Math.sqrt(res);
                display.setText(Double.toString(res));
            } catch (Exception e) {
                display.setText("Error");
            }
            isInputed = true;
        }
        else{
            // 数字
            if(!isInputed && !isInputDouble){
                if(value.equals("0")){
                    return;
                }
                isInputed = true;
                display.clear();
                display.appendText(value);
            }
            else{
                display.appendText(value);
            }
        }
    }

    // 实现计算
    private String evaluateExpression(double a, double b, String opt) {
        switch (opt){
            case "+":
                return Double.toString(a + b);
            case "-":
                return Double.toString(a - b);
            case "×":
                return Double.toString(a * b);
            case "÷":
                return Double.toString(a / b);
            case "%":
                return Double.toString(a % b);
            case "^":
                return Double.toString(Math.pow(a, b));
            default:
                return "Error";
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

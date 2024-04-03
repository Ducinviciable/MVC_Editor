package connect.NOTEPAD;

// import Model.TextModel;
// import View.TextView;

public class TextController {
    public static void main(String[] args) {
        TextModel model = new TextModel();
        TextView view = new TextView(model);
        view.setVisible(true);
    }
}

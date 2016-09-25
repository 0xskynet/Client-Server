
import javax.swing.JOptionPane;
public class MSG {
    NewJFrame gui;
        public String handler(String[] s) {
        String send = "";
        switch(s[1]) {
            case "MSG" :
            {
                   showMessage(s[2],s[3],s[4]);
            } break;
        }
        return send;
    }
private void showMessage(final String title, final String text,final String type) {
    new Thread()
    {
        @Override
        public void run()
        {
            int mtype;            mtype = 0;
            switch(type) {
                case "ERR" : mtype = JOptionPane.ERROR_MESSAGE; break;
                case "INF" : mtype = JOptionPane.INFORMATION_MESSAGE; break;
                case "WAR" : mtype = JOptionPane.WARNING_MESSAGE; break;
                case "QUE" : mtype = JOptionPane.QUESTION_MESSAGE; break;    
                case "PLN" : mtype = JOptionPane.PLAIN_MESSAGE; break;
                default : mtype = JOptionPane.INFORMATION_MESSAGE;
            }
            JOptionPane.showMessageDialog(null, text, title, mtype);
        }
    }.start();
}


}


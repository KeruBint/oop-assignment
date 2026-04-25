package oop;





public class UserInfo {
    private String username;
    private String ID;

    public String getUsername() {
        return username;
    }

    public String getID() {
        return ID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void InputUserInfo(String inputText) {
        this.username = inputText;
        boolean valid = false;
        int spacePos = 0;

        for (int i = 0; i < username.length(); i++) {
            if (username.charAt(i) == ' ') {
                valid = true;
                spacePos = i;
            }
        }

        if (valid && username.length() > 0) {
            char front;
            String back;
            front = Character.toUpperCase(username.charAt(0));
            back = username.substring(spacePos + 1);
            ID = front + back;
        } else {
            ID = "guest";
        }
    }

}

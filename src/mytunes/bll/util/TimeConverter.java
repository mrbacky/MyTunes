package mytunes.bll.util;

public class TimeConverter {

    public String sec_To_Format(int sec) {
        int hours, mins, secs;

        mins = (int) (sec / 60);
        while (mins > 60) {
            mins = mins % 60;
        }
        hours = (int) ((sec / 60) / 60);
        secs = sec % 60;

        String stringTime = String.format("%02d:%02d:%02d", hours, mins, secs);

        return stringTime;// format:   hh:mm:ss
    }

    public int format_To_Sec(String formatString) {
        String[] splited = formatString.split(":");
        //conversion
        System.out.println(splited);
        return 0;

    }
}

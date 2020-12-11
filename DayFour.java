import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class passport {
    int byr;
    int iyr;
    int eyr;
    String hgt;
    String hcl;
    String ecl;
    int pid;
    int cid; //optional

    public int getByr() {
        return byr;
    }

    public int getIyr() {
        return iyr;
    }

    public int getEyr() {
        return eyr;
    }

    public String getHgt() {
        return hgt;
    }

    public String getHcl() {
        return hcl;
    }

    public String getEcl() {
        return ecl;
    }

    public int getPid() {
        return pid;
    }

    public int getCid() {
        return cid;
    }

    public void setByr(int byr) {
        this.byr = byr;
    }

    public void setIyr(int iyr) {
        this.iyr = iyr;
    }

    public void setEyr(int eyr) {
        this.eyr = eyr;
    }

    public void setHgt(String hgt) {
        this.hgt = hgt;
    }

    public void setHcl(String hcl) {
        this.hcl = hcl;
    }

    public void setEcl(String ecl) {
        this.ecl = ecl;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}

public class DayFour {
    public static ArrayList<passport> getPassportData(String fileName) {

        ArrayList<passport> passports = new ArrayList<passport>();
        passport pst = null;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));

            String line = reader.readLine();
            while (line != null) {
                if (line.trim().length() == 0)  //blank line
                {
                    pst = null;
                } else {
                    if (pst == null) {
                        //starting a new passport block, create a new passport record
                        pst = new passport();
                        passports.add(pst);
                    }

                    String [] fields = line.trim().split(" ");
                    String key, value;
                    for (int i = 0; i < fields.length; i++) {
                        String [] pair = new String[2];
                        pair = fields[i].split(":");
                        key = pair[0];
                        value = pair[1];

                        if (key.equalsIgnoreCase("byr")) {
                            pst.setByr(Integer.parseInt(value));
                        } else if (key.equalsIgnoreCase("iyr")) {
                            pst.setIyr(Integer.parseInt(value));
                        } else if (key.equalsIgnoreCase("eyr")) {
                            pst.setEyr(Integer.parseInt(value));
                        } else if (key.equalsIgnoreCase("hgt")) {
                            pst.setHgt(value);
                        } else if (key.equalsIgnoreCase("hcl")) {
                            pst.setHcl(value);
                        } else if (key.equalsIgnoreCase("ecl")) {
                            pst.setEcl(value);
                        } else if (key.equalsIgnoreCase("pid")) {
                            pst.setPid(Integer.parseInt(value));
                        } else if (key.equalsIgnoreCase("cid")) {
                            pst.setCid(Integer.parseInt(value));
                        }
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return passports;
    }

    public static int validPassports(ArrayList<passport> list) {
        int count = 0;
        for (passport p : list) {
            if (p.getByr() != 0 && p.getEcl() != null && p.getEyr() != 0 && p.getHcl() != null
            && p.getHgt() != null && p.getIyr() != 0 && p.getPid() != 0) {
                count++;
            }
        }
        return count;
    }

    public static  void main(String [] args) {
        ArrayList<passport> passports = getPassportData("passports.txt");
        int count = validPassports(passports);

        System.out.println("There are " + count + " valid passports");
    }
}

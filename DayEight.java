import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

class Instruction {
    enum InstructionName {
        ACC(1), JMP(2), NOP(0);
        int value;

        InstructionName(int value) {
            this.value = value;
        }

        int getValue() {
            return value;
        }
    }

    private InstructionName name;
    private char flag;  // + or -
    private int steps;
    private boolean firstTimeAccess;

    public InstructionName getName() {
        return name;
    }

    public void setName(InstructionName name) {
        this.name = name;
    }

    public char getFlag() {
        return flag;
    }

    public void setFlag(char flag) {
        this.flag = flag;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public boolean isFirstTimeAccess() {
        return firstTimeAccess;
    }

    public void setFirstTimeAccess(boolean firstTimeAccess) {
        this.firstTimeAccess = firstTimeAccess;
    }
}

public class DayEight {

    static int accumulatorValue = 0;

    /* get the value in accumulator immediately before any instruction is executed
    * a second time */
    public static int getAccValue(LinkedList<Instruction> list) {
        while (true) {
            for (int i = 0; i < list.size(); i++) {
                Instruction instruction = list.get(i);

                /* both ACC and NOP will continue the next instruction */
                if (!instruction.isFirstTimeAccess()) {
                    if (instruction.getName() == Instruction.InstructionName.ACC) {
                        instruction.setFirstTimeAccess(true);
                        if (instruction.getFlag() == '+')
                            accumulatorValue += instruction.getSteps();
                        else if (instruction.getFlag() == '-')
                            accumulatorValue -= instruction.getSteps();
                    } else if (instruction.getName() == Instruction.InstructionName.NOP) {
                        instruction.setFirstTimeAccess(true);
                    } else if(instruction.getName() == Instruction.InstructionName.JMP) {
                        /* for JMP instruction, it might trigger backtrack instruction */
                        instruction.setFirstTimeAccess(true);
                        if (instruction.getFlag() == '+')
                            i += instruction.getSteps() - 1;
                        else if (instruction.getFlag() == '-') {
                            i = i - instruction.getSteps() - 1;
                        }

                    }
                } else {
                    /* immediately execute an instruction a second time */
                    return accumulatorValue;
                }
            }
        }
    }

    public static Instruction parseLine(String line) {
        String [] strings = line.trim().split(" ");
        Instruction instruction = new Instruction();
        if (strings[0].equalsIgnoreCase("nop"))
            instruction.setName(Instruction.InstructionName.NOP);
        else if (strings[0].equalsIgnoreCase("acc"))
            instruction.setName(Instruction.InstructionName.ACC);
        else if (strings[0].equalsIgnoreCase("jmp"))
            instruction.setName(Instruction.InstructionName.JMP);

        char flag = strings[1].charAt(0);
        instruction.setFlag(flag);
        int step = Integer.parseInt(strings[1].substring(1));
        instruction.setSteps(step);

        return instruction;
    }

    public static LinkedList<Instruction> getInstructions(String fileName) {
        LinkedList<Instruction> instruclist = new LinkedList<Instruction>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                instruclist.add(parseLine(line));
                line = reader.readLine();
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instruclist;
    }

    public static void main(String [] args) {
        LinkedList<Instruction> list = getInstructions("operations.txt");

        System.out.println("The value in the accumulator is " + getAccValue(list));
    }
}

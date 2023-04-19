package utility;

import com.sun.security.jgss.InquireSecContextPermission;
import data.Color;
import data.Coordinates;
import data.Location;
import exceptions.IncorrectInputScripException;
import exceptions.MustBeNotEmptyException;
import exceptions.NotInDeclaredLimitsException;
import run.App;

import javax.naming.ldap.Control;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Ask a user a people value.
 */
public class PersonAsker {
    private final int MAX_X = 835;
    private final int MAX_Y = 196;
    private final float MIN_height = 0;
    private final int MIN_weight = 0;

    private Scanner userScanner;
    private boolean fileMode;

    public PersonAsker(Scanner userScanner){
        this.userScanner = userScanner;
        fileMode = false;
    }

    /**
     * Set a scanner to scan user input.
     * @param userScanner Scanner to set.
     */
    public void setUserScanner(Scanner userScanner){
        this.userScanner = userScanner;
    }

    /**
     * @return Scanner, which uses for user input.
     */
    public Scanner getUserScanner(){
        return userScanner;
    }

    /**
     * Sets person asker mode to 'File Mode'.
     */
    public void setFileMode() {
        this.fileMode = true;
    }

    /**
     * Set person asker mode to 'User Mode'.
     */
    public void setUserMode(){
        this.fileMode = false;
    }

    /**
     * Ask a user the person's name.
     * @return that name.
     * @throws IncorrectInputScripException If script is running and something goes wrong
     */
    public String askName() throws IncorrectInputScripException{
        String name;
        while (true){
            try{
                Console.println("Enter your name:");
                Console.print(App.PS2);
                name = userScanner.nextLine().trim();
                if(fileMode) Console.println(name);
                if(name.equals("")) throw new MustBeNotEmptyException();
                break;
            }catch (NoSuchElementException exception){
                Console.printerror("Name not recognized!");
                if(fileMode) throw new IncorrectInputScripException();
            }catch (MustBeNotEmptyException exception){
                Console.printerror("Name cannot be empty!");
                if(fileMode) throw new IncorrectInputScripException();
            }catch(IllegalStateException exception){
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return name;
    }

    /**
     * Asks a user the person's X coordinate.
     * @return Person's X coordinate.
     * @throws IncorrectInputScripException If script is running and something goes wrong.
     */
    public Long askX() throws IncorrectInputScripException{
        String strX;
        Long x;
        while(true){
            try{
                Console.println("Enter coordinate X < " + (MAX_X + 1) + ":");
                Console.print(App.PS2);
                strX = userScanner.nextLine().trim();
                if(fileMode) Console.println(strX);
                x = Long.parseLong(strX);
                if(x > MAX_X) throw new NotInDeclaredLimitsException();
                break;
            }catch(NoSuchElementException exception){
                Console.printerror("X coordinate not recognized!");
                if(fileMode) throw new IncorrectInputScripException();
            }catch(NotInDeclaredLimitsException exception){
                Console.printerror("X coordinate cannot exceed " + MAX_X +"!");
                if(fileMode) throw new IncorrectInputScripException();
            }catch(NumberFormatException exception){
                Console.printerror("X coordinate must be represented by a number!");
                if(fileMode) throw new IncorrectInputScripException();
            }catch (NullPointerException | IllegalStateException exception){
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return x;
    }

    /**
     * Asks a user the person's Y coordinate.
     * @return Person's Y coordinate.
     * @throws IncorrectInputScripException If script is running and something goes wrong.
     */
    public Float askY() throws IncorrectInputScripException{
        String strY;
        Float y;
        while(true){
            try{
                Console.println("Enter coordinate Y < " + (MAX_Y + 1) + ":");
                Console.print(App.PS2);
                strY = userScanner.nextLine().trim();
                if(fileMode) Console.println(strY);
                y = Float.parseFloat(strY);
                if(y > MAX_Y) throw new NotInDeclaredLimitsException();
                break;
            }catch(NoSuchElementException exception){
                Console.printerror("Y coordinate not recognized!");
                if(fileMode) throw new IncorrectInputScripException();
            }catch(NotInDeclaredLimitsException exception){
                Console.printerror("Y coordinate cannot exceed " + MAX_Y +"!");
                if(fileMode) throw new IncorrectInputScripException();
            }catch(NumberFormatException exception){
                Console.printerror("Y coordinate must be represented by a number!");
                if(fileMode) throw new IncorrectInputScripException();
            }catch (NullPointerException | IllegalStateException exception){
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return y;
    }

    /**
     * Asks a user the person's coordinates.
     * @return Person's coordinates.
     * @throws IncorrectInputScripException If script is running and something goes wrong.
     */
    public Coordinates askCoordinates() throws IncorrectInputScripException{
        Long x;
        Float y;
        x = askX();
        y = askY();
        return new Coordinates(x, y);
    }

    /**
     * Asks a user the person's height.
     * @return Person's height.
     * @throws IncorrectInputScripException If script is running and something goes wrong.
     */
    public float askHeight() throws IncorrectInputScripException{
        String strHeight;
        float height;
        while (true){
            try{
                Console.println("Enter height: ");
                Console.print(App.PS2);
                strHeight = userScanner.nextLine().trim();
                if(fileMode) Console.println(strHeight);
                height = Float.parseFloat(strHeight);
                if(height < MIN_height) throw new NotInDeclaredLimitsException();
                break;
            }catch(NoSuchElementException exception){
                Console.printerror("Height not recognized!");
                if(fileMode) throw new IncorrectInputScripException();
            }catch(NotInDeclaredLimitsException exception){
                Console.printerror("Height must be greater than 0");
                if(fileMode) throw new IncorrectInputScripException();
            }catch(NumberFormatException exception){
                Console.printerror("Height must be represented by a number!");
                if(fileMode) throw new IncorrectInputScripException();
            }catch(NullPointerException | IllegalStateException exception){
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return height;
    }

    /**
     * Asks a user the person's weight.
     * @return Person's weight.
     * @throws IncorrectInputScripException If script is running and something goes wrong.
     */
    public int askWeight() throws IncorrectInputScripException{
        String strWeight;
        int weight;
        while(true){
            try{
                Console.println("Enter weight: ");
                Console.print(App.PS2);
                strWeight = userScanner.nextLine().trim();
                if(fileMode) Console.println(strWeight);
                weight = Integer.parseInt(strWeight);
                if(weight < MIN_weight) throw new NotInDeclaredLimitsException();
                break;
            }catch(NoSuchElementException exception){
                Console.printerror("Weight not recognized!");
                if(fileMode) throw new IncorrectInputScripException();
            }catch(NotInDeclaredLimitsException exception){
                Console.printerror("Weight must be greater than 0");
                if(fileMode) throw new IncorrectInputScripException();
            }catch(NumberFormatException exception){
                Console.printerror("Weight must be represented by a number!");
                if(fileMode) throw new IncorrectInputScripException();
            }catch(NullPointerException | IllegalStateException exception){
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return weight;
    }

    /**
     * Asks a user the person's hair color.
     * @return person's hair color.
     * @throws IncorrectInputScripException If script running and something goes wrong.
     */
    public Color askColor() throws IncorrectInputScripException{
        String strColor;
        Color color;
        while(true){
            try{
                Console.println("Color list: " +Color.nameList());
                Console.println("Enter hair color: ");
                Console.print(App.PS2);
                strColor = userScanner.nextLine().trim();
                if(fileMode) Console.println(strColor);
                color = Color.valueOf(strColor.toUpperCase());
                break;
            }catch(NoSuchElementException exception){
                Console.printerror("Color not recognized!");
                if(fileMode) throw new IncorrectInputScripException();
            }catch (IllegalArgumentException exception){
                Console.printerror("Color is't in list!");
                if(fileMode) throw new IncorrectInputScripException();
            }catch (IllegalStateException exception){
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return color;
    }

    /**
     * Asks a user the person's X location.
     * @return Person's X location.
     * @throws IncorrectInputScripException If script is running and something goes wrong.
     */
    public int askXLocation() throws IncorrectInputScripException{
        String strX;
        int x;
        while (true) {
            try {
                Console.println("Enter X location: ");
                Console.print(App.PS2);
                strX = userScanner.nextLine().trim();
                if (fileMode) Console.println(strX);
                x = Integer.parseInt(strX);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("X location not recognized!");
                if (fileMode) throw new IncorrectInputScripException();
            } catch (NumberFormatException exception) {
                Console.printerror("X location must be a number!");
                if (fileMode) throw new IncorrectInputScripException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return x;
    }

    /**
     * Asks a user the person's Y location.
     * @return Person's Y location.
     * @throws IncorrectInputScripException If script is running and something goes wrong.
     */
    public long askYLocation() throws IncorrectInputScripException{
        String strY;
        long y;
        while (true) {
            try {
                Console.println("Enter Y location: ");
                Console.print(App.PS2);
                strY = userScanner.nextLine().trim();
                if (fileMode) Console.println(strY);
                y = Long.parseLong(strY);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Y location not recognized!");
                if (fileMode) throw new IncorrectInputScripException();
            } catch (NumberFormatException exception) {
                Console.printerror("Y location must be a number!");
                if (fileMode) throw new IncorrectInputScripException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return y;
    }

    /**
     * Asks a user the person's Z location.
     * @return Person's Z location.
     * @throws IncorrectInputScripException If script is running and something goes wrong.
     */
    public Integer askZLocation() throws IncorrectInputScripException{
        String strZ;
        int z;
        while (true) {
            try {
                Console.println("Enter Z location: ");
                Console.print(App.PS2);
                strZ = userScanner.nextLine().trim();
                if (fileMode) Console.println(strZ);
                z = Integer.parseInt(strZ);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Z location not recognized!");
                if (fileMode) throw new IncorrectInputScripException();
            } catch (NumberFormatException exception) {
                Console.printerror("Z location must be a number!");
                if (fileMode) throw new IncorrectInputScripException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return z;
    }

    /**
     * Asks a user the person's location.
     * @return Person's location.
     * @throws IncorrectInputScripException If script is running and something goes wrong.
     */
    public Location askLocation() throws IncorrectInputScripException{
        int x;
        long y;
        Integer z;
        x = askXLocation();
        y = askYLocation();
        z = askZLocation();
        return new Location(z, y, z);
    }

    /**
     * Asks a user person's birthday.
     * @return Person's birthday.
     * @throws IncorrectInputScripException If script is running and something goes wrong.
     */
    public ZonedDateTime askBirthday() throws IncorrectInputScripException{
        String strBirthday;
        ZonedDateTime birthday;
        while(true){
            try{
                Console.println("Enter person's birthday (dd/MM/yyyy): ");
                strBirthday = userScanner.nextLine().trim();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(strBirthday, formatter);
                birthday = ZonedDateTime.of(date, LocalTime.of(0, 0), ZoneId.systemDefault());
                break;
            }catch (NoSuchElementException exception){
                Console.printerror("Birthday not recognized!");
                if(fileMode) throw new IncorrectInputScripException();
            }catch(DateTimeParseException exception){
                Console.printerror("Invalid format, please enter person's birthday in (dd/MM/yyyy) format!");
                if(fileMode) throw new IncorrectInputScripException();
            }catch (NullPointerException | IllegalStateException exception){
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return birthday;
    }

    /**
     * Asks a user a question.
     * @return Answer (true/false).
     * @param question A question.
     * @throws IncorrectInputScripException If script is running and something goes wrong.
     */
    public boolean askQuestion(String question) throws IncorrectInputScripException {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                Console.println(finalQuestion);
                Console.print(App.PS2);
                answer = userScanner.nextLine().trim();
                if (fileMode) Console.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Answer not recognized!");
                if (fileMode) throw new IncorrectInputScripException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("The answer must be in characters '+' or '-'!");
                if (fileMode) throw new IncorrectInputScripException();
            } catch (IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return (answer.equals("+")) ? true : false;
    }

    @Override
    public String toString(){
        return "PersonAsker (helper class for user requests).";
    }
}

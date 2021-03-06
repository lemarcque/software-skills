/**
 * MIT License
 *
 * Copyright (c) 2018 Henoc Sese
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    // Application propreties
    private static int APP_WIDTH = 400;
    private static int APP_HEIGHT = 400;
    private static String APP_NAME = "Sorting algorithm";

    // List
    private List<Integer> barValueList = new ArrayList<>();
    private List<Bar> barList;

    // JavaFX node
    private Group root;
    private Stage stage;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Creation and affectation of the Scene

        root = new Group();
        Scene scene = new Scene(root, APP_WIDTH, APP_HEIGHT);

        stage = primaryStage;
        stage.setTitle(APP_NAME);
        stage.setScene(scene);
        stage.show();

        // generate set of data
        generateList();
        displayList();

        // List to keyboard event
        keyboardEventManager();

        // TODO : Remove this method call
        TestEnvironnement env = new TestEnvironnement();
    }


    /**
     * Generate a list of integer representing
     * value of the Bar
     */
    private void generateList() {
        // Generate a random integer of that define the number of data (i.e, Bar that will be displayed)
        // final int min = 10;
        // final int max = 150;
        // int randValue = min + (int) (Math.random() * (max - min);

        final int randValue = 20;

        barValueList = new ArrayList<>();

        // add item on the list
        for(int i = 1; i < randValue + 1; i++) {
            barValueList.add(i * (APP_HEIGHT / randValue));
        }
    }

    /**
     * Display bar chart with Bar object
     */
    private void displayList() {
        Bar a = new Bar(100, 100);
        Bar b = new Bar(100, 100);


        barList = new ArrayList<>();
        final int width = (APP_WIDTH / barValueList.size());   // width of bar

        for (int i = 0; i < barValueList.size(); i++) {

            int value = barValueList.get(i);

            // Add new rectangle of the root scene
            Bar bar = new Bar(value, width);
            bar.setLayoutX(i * bar.getWidth());
            bar.setLayoutY(APP_HEIGHT - bar.getHeight());
            barList.add(bar);
            root.getChildren().add(barList.get(barList.size() - 1));
        }
    }

    /**
     * Make the list in disorder
     */
    private void disorderList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int minValue = barList.get(0).getValue();
                int maxValue = barList.get(barList.size() -1 ).getValue();


                for(int index = 0; index < barList.size(); index++) {

                    // Minimum and Maximum random element
                    final int min = 0;
                    final int max = barList.size();
                    int randIndex;

                    do {
                       randIndex = min + (int) (Math.random() * (max - min));
                    }while(randIndex == index);

                    // Get a random bar
                    barList.get(randIndex).setOrdered(false);
                    barList.get(index).setOrdered(false);


                    // Swapping

                    // Permutation in the List (Collection)
                    final Bar tempCurrentBar = barList.get(index);
                    barList.set(index, barList.get(randIndex));
                    barList.set(randIndex, tempCurrentBar);

                    // Permutation of the X position on the display
                    final int tmpBarX = (int) barList.get(index).getLayoutX();
                    barList.get(index).setLayoutX(barList.get(randIndex).getLayoutX());
                    barList.get(randIndex).setLayoutX(tmpBarX);

                    // Pause the thread
                    final int animationDelay = 150; //3000
                    try {
                        Thread.sleep(animationDelay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

    }

    /**
     * Testing a variety of sorting's algorithm
     */
    private void testSorting() {

        // Bubble Sort
        BarBubbleSort.sortLive(barList);
    }

    /**
     * Handle keyboard press event
     */
    private void keyboardEventManager() {
        stage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {

            switch (event.getCode()) {
                
                case SPACE:
                    // generate new disorder list
                    disorderList();
                    break;
                    
                case S:
                    // order the list with an sorting algorithm's
                    testSorting();
                    break;
                    
                    default:
            }
        });
    }

}

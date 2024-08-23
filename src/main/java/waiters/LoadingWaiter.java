package waiters;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class LoadingWaiter {



    public static void waitUntilSubjectIsDisplayed(WebElement element, String text) {
        for (int i = 0; i < 20; i++) {
            System.out.println(i);

            try {
                if (element.getText().equals(text)) {
                    return;
                }
            } catch (NoSuchElementException e) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
    }
}

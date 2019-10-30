package pl.codinglab.bookpublisher;

import pl.codinglab.bookpublisher.model.BookTestData;
import pl.codinglab.bookpublisher.model.BookRepository;
import pl.codinglab.bookpublisher.view.main.MainView;
import pl.codinglab.bookpublisher.view.main.MainViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.cdi.MvvmfxCdiApplication;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Locale;
import java.util.ResourceBundle;

public class App extends MvvmfxCdiApplication {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    @Inject
    private ResourceBundle resourceBundle;
    @Inject
    private BookRepository bookRepository;

    @Override
    public void initMvvmfx()  {
        bookRepository.saveAll(BookTestData.createTestBooks());
    }

    @Override
    public void startMvvmfx(Stage stage) {
        LOG.info("Starting the Application");
        MvvmFX.setGlobalResourceBundle(resourceBundle);
        stage.setTitle(resourceBundle.getString("window.title"));
        ViewTuple<MainView, MainViewModel> main = FluentViewLoader.fxmlView(MainView.class).load();
        Scene scene = new Scene(main.getView());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.GERMAN);
        launch(args);
    }
}

//package nl.han.ica.dea.datasource.dao;
//
//import nl.han.ica.dea.datasource.datamappers.LoginResponseDataMapper;
//import nl.han.ica.dea.service.ConnectionService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.mockito.Mockito;
//
//public class LoginDAOTest {
//
//    private LoginDAO sut;
//    private LoginResponseDataMapper mockedLoginResponseDataMapper;
//    private ConnectionService mockedConnectionService;
//
//    @BeforeEach
//    public void setup() {
//        sut = new LoginDAO();
//        mockedLoginResponseDataMapper = Mockito.mock(LoginResponseDataMapper.class);
//        mockedConnectionService = Mockito.mock(ConnectionService.class);
//        sut.setLoginResponseDataMapper(mockedLoginResponseDataMapper);
//        sut.setConnectionService(mockedConnectionService);
//    }
//
//    @Nested
//    public class FnCheckCredentials {
//
//    }
//}

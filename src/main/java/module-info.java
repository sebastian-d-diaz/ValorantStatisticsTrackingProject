module ca.ucalgary.part3groupprojectcpsc233 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ca.ucalgary.part3groupprojectcpsc233.app to javafx.fxml;
    exports  ca.ucalgary.part3groupprojectcpsc233.app;
    exports ca.ucalgary.part3groupprojectcpsc233;
    exports ca.ucalgary.part3groupprojectcpsc233.enums;
    exports ca.ucalgary.part3groupprojectcpsc233.objects;
    exports ca.ucalgary.part3groupprojectcpsc233.util;
    exports ca.ucalgary.part3groupprojectcpsc233.comparators;
}
package sgb.apresentacao;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("")
public class HomePage extends AppLayout {

    public HomePage() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Apoio Psicopedagógico CESAR School");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        SideNav nav = getSideNav();

        Scroller scroller = new Scroller(nav);
        scroller.setClassName(LumoUtility.Padding.SMALL);

        addToDrawer(scroller);
        addToNavbar(toggle, title);
    }

    private SideNav getSideNav() {
        SideNav sideNav = new SideNav();
        sideNav.addItem(
                new SideNavItem("Início", "",
                        VaadinIcon.HOME.create()),
                new SideNavItem("Criar Atendimento", "/atendimento/criacao", VaadinIcon.CLIPBOARD_TEXT.create()),
                new SideNavItem("Atendimentos", "/atendimento/historico",
                        VaadinIcon.RECORDS.create()),
                new SideNavItem("Marcadores", "/marcador/criacao",
                        VaadinIcon.TAGS.create()),
                new SideNavItem("Biblioteca Alunos ", "aluno/biblioteca",
                        VaadinIcon.USERS.create()),
                new SideNavItem("Analytics", "/analytics",
                        VaadinIcon.CHART.create()),
                new SideNavItem("Ajuda", "/suporte", VaadinIcon.EXCLAMATION_CIRCLE.create()));
        return sideNav;
    }
}

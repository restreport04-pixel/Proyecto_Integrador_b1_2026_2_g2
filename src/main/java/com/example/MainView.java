package com.example;

import com.example.dao.EspacioDAO;
import com.example.dao.ReservaDAO;
import com.example.dao.TipoEspacioDAO;
import com.example.dao.UsuarioDAO;
import com.example.model.Espacio;
import com.example.model.Reserva;
import com.example.model.TipoEspacio;
import com.example.model.Usuario;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.jdbc.core.JdbcTemplate;

@PageTitle("Gestión Coworking - CRUD")
@Route("")
public class MainView extends VerticalLayout {

    private final UsuarioDAO usuarioDAO;
    private final TipoEspacioDAO tipoEspacioDAO;
    private final EspacioDAO espacioDAO;
    private final ReservaDAO reservaDAO;

    public MainView(JdbcTemplate jdbcTemplate) {
        this.usuarioDAO = new UsuarioDAO(jdbcTemplate);
        this.tipoEspacioDAO = new TipoEspacioDAO(jdbcTemplate);
        this.espacioDAO = new EspacioDAO(jdbcTemplate);
        this.reservaDAO = new ReservaDAO(jdbcTemplate);

        setSizeFull();
        setPadding(true);
        setSpacing(true);

        H2 titulo = new H2("Sistema de Gestión de Coworking");

        TabSheet tabSheet = new TabSheet();
        tabSheet.setWidthFull();

        tabSheet.add("Usuarios", crearSeccionUsuarios());
        tabSheet.add("Tipos de Espacio", crearSeccionTipoEspacio());
        tabSheet.add("Espacios", crearSeccionEspacios());
        tabSheet.add("Reservas", crearSeccionReservas());

        add(titulo, tabSheet);
    }

    private Component crearSeccionUsuarios() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        Grid<Usuario> grid = new Grid<>(Usuario.class, false);
        grid.addColumn(u -> u.getId()).setHeader("ID");
        grid.addColumn(u -> u.getNombre()).setHeader("Nombre");
        grid.addColumn(u -> u.getEmail()).setHeader("Email");
        grid.addColumn(u -> u.getTelefono()).setHeader("Teléfono");

        grid.setItems(usuarioDAO.obtenerTodos());

        layout.add(grid);
        return layout;
    }

    private Component crearSeccionTipoEspacio() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        Grid<TipoEspacio> grid = new Grid<>(TipoEspacio.class, false);
        grid.addColumn(t -> t.getId()).setHeader("ID");
        grid.addColumn(t -> t.getNombre()).setHeader("Nombre");
        grid.addColumn(t -> t.getDescripcion()).setHeader("Descripción");

        grid.setItems(tipoEspacioDAO.obtenerTodos());

        layout.add(grid);
        return layout;
    }

    private Component crearSeccionEspacios() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        Grid<Espacio> grid = new Grid<>(Espacio.class, false);
        grid.addColumn(e -> e.getId()).setHeader("ID");
        grid.addColumn(e -> e.getNombre()).setHeader("Nombre");
        grid.addColumn(e -> e.getCapacidad()).setHeader("Capacidad");
        grid.addColumn(e -> e.getPrecioHora()).setHeader("Precio/Hora");
        grid.addColumn(e -> e.getTipoEspacioId()).setHeader("Tipo Espacio ID");

        grid.setItems(espacioDAO.obtenerTodos());

        layout.add(grid);
        return layout;
    }

    private Component crearSeccionReservas() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        Grid<Reserva> grid = new Grid<>(Reserva.class, false);
        grid.addColumn(r -> r.getId()).setHeader("ID");
        grid.addColumn(r -> r.getUsuarioId()).setHeader("Usuario ID");
        grid.addColumn(r -> r.getEspacioId()).setHeader("Espacio ID");
        grid.addColumn(r -> r.getFechaInicio()).setHeader("Fecha Inicio");
        grid.addColumn(r -> r.getFechaFin()).setHeader("Fecha Fin");
        grid.addColumn(r -> r.getTotalPagar()).setHeader("Total Pagar");

        grid.setItems(reservaDAO.obtenerTodos());

        layout.add(grid);
        return layout;
    }
}
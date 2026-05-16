graph LR
    subgraph PMS_Hotelero[Sistema de Gestión PMS]
        UC1(Iniciar Sesión)
        UC2(Consultar Dashboard)
        UC3(Gestionar Habitaciones)
        UC4(Crear Reserva Telefónica)
        UC5(Realizar Check-in Grupal)
        UC6(Gestionar Ficha de Huésped)
        UC7(Finalizar Estancia / Checkout)
    end

    Empleado((Empleado)) --> UC1
    Empleado --> UC2
    Empleado --> UC3
    Empleado --> UC4
    Empleado --> UC5
    Empleado --> UC6
    Empleado --> UC7

    style Empleado fill:#f9f,stroke:#333,stroke-width:2px
    style PMS_Hotelero fill:#f5f5f5,stroke:#0070C0,stroke-width:2px
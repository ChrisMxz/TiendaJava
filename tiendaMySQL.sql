--Tabla cliente

CREATE TABLE
    `cliente` (
        `id_cliente` int(5) NOT NULL AUTO_INCREMENT PRIMARY KEY,
        `nombre` varchar(20) NOT NULL,
        `appaterno` varchar(15) NOT NULL,
        `apmaterno` varchar(15) NOT NULL,
        `edad` int(3) NOT NULL,
        `sexo` varchar(10) NOT NULL,
        `direccion` varchar(500) NOT NULL,
        `telefono` varchar(10) NOT NULL
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

--Datos de clientes

INSERT INTO
    `cliente` (
        `id_cliente`,
        `nombre`,
        `appaterno`,
        `apmaterno`,
        `edad`,
        `sexo`,
        `direccion`,
        `telefono`
    )
VALUES (
        1,
        "David",
        "Dorantes ",
        "Martinez",
        22,
        "M",
        "calle 44",
        "9078980877"
    ), (
        2,
        "Jesus",
        "D",
        "M",
        17,
        "M",
        "Calle 4",
        "4353453534"
    ), (
        3,
        "Laura",
        "A",
        "L",
        21,
        "F",
        "Manzana",
        "4534645456"
    ), (
        4,
        "Miguel",
        "D",
        "C",
        33,
        "M",
        "Durazno\nMéxico",
        "5234453423"
    ), (
        5,
        "Paula",
        "M",
        "H",
        40,
        "F",
        "Estado de méxico",
        "5645645645"
    ), (
        6,
        "Cristofert",
        "H",
        "M",
        25,
        "M",
        "México calle Limon",
        "2341231231"
    ), (
        7,
        "Jose",
        "C",
        "J",
        21,
        "M",
        "Calle 7",
        "2312312312"
    ), (
        8,
        "Monserrat",
        "R",
        "V",
        25,
        "F",
        "Calle Melón",
        "1231231231"
    ), (
        9,
        "Johan",
        "G",
        "T",
        22,
        "M",
        "calle 10",
        "2312313123"
    ), (
        10,
        "Daniel",
        "I",
        "S",
        27,
        "M",
        "Calle Toluca\nMéxico\t",
        "5345466784"
    ), (
        11,
        "Victor",
        "F",
        "P",
        24,
        "M",
        "Hidalgo\t calle luna",
        "2332343245"
    ), (
        12,
        "Dana",
        "Scully",
        "A",
        27,
        "F",
        "DC",
        "4523423465"
    ), (
        13,
        "Fox",
        "Mulder",
        "S",
        30,
        "M",
        "California EU",
        "5523123465"
    ), (
        14,
        "John",
        "Doggett",
        "J",
        45,
        "M",
        "California",
        "5523213123"
    ), (
        15,
        "Walter",
        "Skinner",
        "W",
        52,
        "M",
        "DC",
        "5345342342"
    ), (
        16,
        "Alex",
        "Krycek",
        "X",
        34,
        "M",
        "Sin Numero\t",
        "1323312312"
    );

--Tabla Concepto

CREATE TABLE
    `concepto` (
        `id_concepto` int(10) NOT NULL PRIMARY KEY,
        `concepto` varchar(500) NOT NULL
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

--Datos de concepto

INSERT INTO
    `concepto` (`id_concepto`, `concepto`)
VALUES (1, "Papel Baño"), (2, "Auto Clasico"), (3, "Memoria"), (4, "Celular"), (5, "Teclado"), (6, "Jabon Baño"), (7, "Escoba Piso"), (
        32101601,
        "Memoria de acceso aleatorio "
    ), (
        52141501,
        "Neveras para uso doméstico "
    ), (52141502, "Hornos microondas"), (
        52141510,
        "Aire acondicionado portátil"
    ), (
        52141516,
        "Freidoras para uso doméstico "
    ), (
        52141524,
        "Licuadoras para uso doméstico "
    ), (
        52161505,
        "Televisores (Pantallas) "
    ), (
        52161508,
        "Reproductores de discos laser "
    ), (52161511, "Radios "), (52161514, "Audífonos"), (52161520, "Micrófonos "), (
        52161521,
        "Receptores de multimedia "
    ), (
        52161557,
        "Consola fija de juegos"
    );

--Tabla producto

CREATE TABLE
    `producto` (
        `id_producto` int(5) NOT NULL AUTO_INCREMENT PRIMARY KEY,
        `id_concepto` int(10) NOT NULL,
        `nombre` varchar(15) NOT NULL,
        `descripcion` varchar(500) NOT NULL,
        `stock` int(10) NOT NULL,
        `precio` double(10, 2) NOT NULL,
        KEY `id_concepto` (`id_concepto`),
        CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`id_concepto`) REFERENCES `concepto` (`id_concepto`) ON UPDATE CASCADE
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

---

--Datos de productos

---

INSERT INTO
    `producto` (
        `id_producto`,
        `id_concepto`,
        `nombre`,
        `descripcion`,
        `stock`,
        `precio`
    )
VALUES (
        1,
        5,
        "Teclado gamer",
        "Teclado completo",
        95,
        100.00
    ), (
        2,
        52141516,
        "Chefman",
        "La freidora multifuncional Chefman se encarga de cualquier tarea de cocinar, hornear, asar, deshidratar, rostizar o freír al aire. Contiene 14 funciones preestablecidas",
        7,
        3679.00
    ), (
        3,
        52161520,
        "HyperX QuadCast",
        "Micrófono USB Condensador para Gaming y Streaming, compatible con PC, PS4 y Mac, Montura Antivibraciones, Pop Filter, Cuatro patrones polares, Podcasts, YouTube",
        5,
        1631.00
    ), (
        5,
        52161520,
        "Razer Seiren V2",
        "Micrófono USB para Streamers, de condensador de 25 mm - Patrón de captación supercardioide",
        11,
        2049.00
    ), (
        6,
        52161520,
        "Blue Yeti",
        "Micrófono USB para Grabación, Streaming, Gaming, Podcasts en PC y Mac, Micrófono de Condensador para Laptop o Computadora, Efectos Blue VO!CE, Base Ajustable - Negro",
        3,
        2018.00
    ), (
        7,
        32101601,
        "Corsair",
        "Vengeance LPX, 16 GB, DDR4, DRAM, 3200 MHz, Black",
        9,
        1329.67
    );

---

---Tabla pedidos

---

CREATE TABLE
    `pedido` (
        `id_pedido` int(5) NOT NULL AUTO_INCREMENT PRIMARY KEY,
        `id_cliente` int(5) NOT NULL,
        `cantidadproductos` int(5) DEFAULT NULL,
        `total` double(10, 4) DEFAULT NULL,
        `fecha` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
        `estado` varchar(15) NOT NULL,
        KEY `cliente` (`id_cliente`),
        CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON UPDATE CASCADE
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

--Tabla items

CREATE TABLE
    `item` (
        `id_item` int(5) NOT NULL AUTO_INCREMENT PRIMARY KEY,
        `id_pedido` int(5) NOT NULL,
        `id_producto` int(5) NOT NULL,
        `cantidad` int(5) NOT NULL,
        `monto` double(10, 4) DEFAULT NULL,
        KEY `pedido` (`id_pedido`),
        KEY `id_producto` (`id_producto`),
        CONSTRAINT `item_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`) ON UPDATE CASCADE,
        CONSTRAINT `item_ibfk_2` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`) ON UPDATE CASCADE
    ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
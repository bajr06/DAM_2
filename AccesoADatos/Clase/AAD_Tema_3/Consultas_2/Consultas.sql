-- Ejercicio base
select Nombre from Jugador
	inner join
		Estadisticas
	on Jugador.Codigo = Estadisticas.Jugador;

-- Ejercicio 1
select Nombre, Total from
	(select Codigo, Nombre from Jugadores) T1
	inner join
		(select sum(Puntos_por_Partido) as Total, Jugador from Estadisticas 
		group by Jugador) T2
	on T1.Codigo = T2.Jugador;

-- Ejercicio 2
select avg(Total) Media, Nombre_Equipo from
	(select Nombre, Total, Nombre_Equipo from Jugadores) T1
	inner join
		(select sum(Puntos_por_Partido) as Total, Jugador from Estadisticas 
		group by Jugador) T2
	on T1.Codigo = T2.Jugador
group by Nombre_Equipo
having media > 60;

-- Ejercicio 3
select avg(Total) Media, Nombre_Equipo from
	(select Nombre, Total, Nombre_Equipo from Jugadores
	where Nombre_Equipo like 'A%') T1
	inner join
		(select sum(Puntos_por_Partido) as Total, Jugador from Estadisticas 
		group by Jugador) T2
	on T1.Codigo = T2.Jugador
group by Nombre_Equipo;

-- Ejercicio 4
select *from Partidos
left join Partidos
on Partidos.Equipo_Local = Equipos.Nombre;

-- Ejercicio 5
select Nombre from Estudiantes
	left join
		Asignatura_Nota
	on Estudiante.ID = Asignatura_Nota.ID_Estudiante
where ID_Estudiante is null;
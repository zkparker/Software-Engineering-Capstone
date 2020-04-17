/*
 * Copyright [2020] [ElEspada - Software Engineering Capstone - Springfield, IL]
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.elespada;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.elespada.model.Menu;
import com.elespada.repo.MenuRepository;

@SpringBootApplication
public class ElEspadaApplication extends SpringBootServletInitializer implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(ElEspadaApplication.class);

	@Autowired
	MenuRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(ElEspadaApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ElEspadaApplication.class);
	}
	
	/**
	 * Runs on spring boot application load
	 */
	@Override
	public void run(String... args) throws Exception {
		logger.debug("Loading Menu into in-memory database on application start");
		Menu lunch1 = new Menu(11l, "Los Noches Cheese Steak Tacos", "Lunch", "One grilled flour tortilla filled with steak strips, onions, \n" + 
				"cheesy cream sauce, and topped with lettuce.", 8.50f, "losnochestacos.png");
		Menu lunch2 = new Menu(12l, "Quesadilla Deluxe", "Lunch", "One chicken quesadilla", 8.00f, "quesadilla.png");
		Menu lunch3 = new Menu(13l, "Tacos de Azada", "Lunch", "Two grilled corn tortillas filled with grilled meat, onions, and cilantro. "
				, 9.50f, "tacosdeazada.png");
		Menu lunch4 = new Menu(14l, "Tacos Cero", "Lunch", "Two grilled flour tortilla filled with grilled meat, \n" + 
				"					fresh Pico de Gallo, avocado, and creamy chipotle sauce.", 10.00f, "tacoscero.png");
		Menu lunch5 = new Menu(15l, "Sonido", "Lunch", "One taco, one enchilada", 7.00f, "sonido.png");
		Menu lunch6 = new Menu(16l, "Burrito Specito", "Lunch", "A burrito filled with beef and refried beans, topped with lettuce, tomatoes, and sour cream.", 6.50f, "burrito.png");
		Menu lunch7 = new Menu(17l, "Enchiladas Blancas", "Lunch", "One chicken enchilada and one beef enchilada topped with our cheese sauce, lettuce, sour cream, \n" + 
				"					tomatoes, and rice.", 8.50f, "enchiladasblancas.png");
		Menu dinner1 = new Menu(41l, "El Arrancar", "Dinner", "A grilled flour tortilla shell in a shape of a volcano filled with onions, bell peppers, tomatoes, \n" + 
				"					steak strips. \n" + 
				"					Held together by a pepper ring and topped with our homemade creamy cheese sauce.", 13.00f, "elarrancar.png");
		Menu dinner2 = new Menu(42l, "Nachos Noches", "Dinner", "Chips topped with ground beef, shredded chicken, beans, cheese, lettuce, tomatoes, and sour cream.", 10.00f, "nachos.png");
		Menu dinner3 = new Menu(43l, "Chimichanga", "Dinner", "Two fried rolled up flour tortillas filled with chicken. \n" + 
				"					Topped with cheese sauce, lettuce, tomato, sour cream and guacamole.", 10.50f, "chimichanga.png");
		Menu dinner4 = new Menu(44l, "Heuco Mundo", "Dinner", "Grilled chicken, steak strips, shrimp, onions, bell peppers, \n" + 
				"					and tomatoes over rice coved with cheese sauce. Comes with flour tortillas.", 10.00f, "heuco.png");
		Menu dinner5 = new Menu(45l, "Flautas", "Dinner", "Three fried and rolled corn tortillas filled with chicken, \n" + 
				"					topped with Chipotle cream sauce, lettuce, shredded cheese, Pico de Gallo, and rice.", 11.00f, "flautas.png");
		Menu dinner6 = new Menu(46l, "Huevos con Chorizo", "Dinner", "Three eggs scrambled with chorizo (Homemade Mexican Sausage). \n" + 
				"					Comes with flour tortillas.", 9.50f, "chorizo.png");
		Menu dessert1 = new Menu(81l, "Flan", "Dessert", "Mexican Custard with whipped cream and cherry on top.", 3.50f, "flan.png");
		Menu dessert2 = new Menu(82l, "Churros", "Dessert", "Three flour dough sticks deep fried. Covered with cinnamon, sugar, \n" + 
				"					chocolate syrup, whipped cream and cherry on top.", 4.00f, "churros.png");
		Menu dessert3 = new Menu(83l, "Chimi Cheesecake", "Dessert", "A rolled-up deep fried flour tortilla filled with cheesecake. \n" + 
				"					Cover with sugar cinnamon, chocolate syrup, whipped cream and cherry on top.", 5.00f, "cheesecake.png");
		Menu dessert4 = new Menu(84l, "Fried Ice Cream", "Dessert", "A fried flour tortilla bowl filled with a vanilla ice cream ball covered with cereal that’s deep fried.\n" + 
				"					 Covered with honey, cinnamon, whipped cream, chocolate syrup and cherry on top.", 4.50f, "friedicecream.png");
		repository.save(lunch1);
		repository.save(lunch2);
		repository.save(lunch3);
		repository.save(lunch4);
		repository.save(lunch5);
		repository.save(lunch6);
		repository.save(lunch7);
		repository.save(dinner1);
		repository.save(dinner2);
		repository.save(dinner3);
		repository.save(dinner4);
		repository.save(dinner5);
		repository.save(dinner6);
		repository.save(dessert1);
		repository.save(dessert2);
		repository.save(dessert3);
		repository.save(dessert4);
	}

}

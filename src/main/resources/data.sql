delete from Taco_Order_Tacos;
delete from Taco_Taco_Ingredients;
delete from Taco;
delete from Taco_Order;

delete from Taco_Ingredient;

insert into Taco_Ingredient (id, name, type)
values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into Taco_Ingredient (id, name, type)
values ('COTO', 'Corn Tortilla', 'WRAP');
insert into Taco_Ingredient (id, name, type)
values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into Taco_Ingredient (id, name, type)
values ('CARN', 'Carnitas', 'PROTEIN');
insert into Taco_Ingredient (id, name, type)
values ('TMTO', 'Diced Tomatoes', 'VEGGIE');
insert into Taco_Ingredient (id, name, type)
values ('LETC', 'Lettuce', 'VEGGIE');
insert into Taco_Ingredient (id, name, type)
values ('CHED', 'Cheddar', 'CHEESE');
insert into Taco_Ingredient (id, name, type)
values ('JACK', 'Monterrey Jack', 'CHEESE');
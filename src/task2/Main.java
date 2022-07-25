package task2;

import java.util.*;

import static java.util.Arrays.asList;

/**
 * Из коллеции объектов `Person` необходимо:
 * 1. Найти количество несовершеннолетних (т.е. людей младше 18 лет).
 * 2. Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
 * 3. Получить отсортированный по фамилии список потенциально работоспособных людей
 * с высшим образованием в выборке (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин).
 */

public class Main {
    static final long NUMBER = 10_000_000;

    public static void main(String[] args) {
        List<String> namesBoys = Arrays.asList("Иван", "Алексей", "Олег", "Максим", "Денис", "Василий");
        List<String> namesGirls = Arrays.asList("Наталья", "Анна", "Мария", "Олеся", "Виктория", "Анастасия");
        List<String> names = new ArrayList<>();
        names.addAll(namesBoys);
        names.addAll(namesGirls);
        List<String> families = asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < NUMBER; i++) {
            String name = names.get(new Random().nextInt(names.size()));
            persons.add(new Person(
                    name,
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    nameSexMan(namesBoys, name),
                    Education.values()[new Random().nextInt(Education.values().length)]
            ));
        }

//        for (Person person : persons) {
//            System.out.println(person);
//        }

        long count = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + count);

        List<String> listFamilies = persons.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() >= 18 && x.getAge() < 27)
                .map(Person::getFamily).toList();
        System.out.println("Список призывников: \n" + listFamilies);

        List<Person> listEducation = persons.stream()
                .filter(x -> {
                    if (x.getSex() == Sex.MAN) {
                        return x.getAge() < 65 && x.getAge() >= 18;
                    } else return x.getAge() < 60 && x.getAge() > 18;
                })
                .filter(x -> x.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily)
                        .thenComparing(Person::getName)
                        .thenComparing(Person::getAge))
                .toList();

        System.out.println("Список потенциально работоспособных людей с высшим образованием: ");
        for (Person person : listEducation) {
            System.out.println(person);
        }
    }

    public static Sex nameSexMan(List<String> names, String name) {
        for (String s : names) {
            if (s.equals(name))
                return Sex.MAN;
        }
        return Sex.WOMAN;
    }
}





package task2;

import java.util.*;

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
        List<String> names = Arrays.asList("Иван", "Алексей", "Наталья", "Анна", "Олег", "Максим", "Денис", "Василий");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < NUMBER; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]
            ));
        }

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

        System.out.println("Cписок потенциально работоспособных людей с высшим образованием: ");
        for (Person person : listEducation) {
            System.out.println(person);
        }


    }
}


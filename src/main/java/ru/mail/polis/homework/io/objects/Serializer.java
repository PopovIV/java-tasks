package ru.mail.polis.homework.io.objects;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Нужно реализовать методы этого класса и реализовать тестирование 4-ех способов записи.
 * Для тестирования надо создать список из 1000+ разных объектов (заполнять объекты можно рандомом,
 * с помощью класса Random). Важно, чтобы в списке животных попадались null-ы
 * Потом получившийся список записать в файл (необходимо увеличить размер списка, если запись происходит менее 5 секунд).
 * НЕ должно быть ссылок на одни и те же объекты
 *
 * Далее этот список надо прочитать из файла.
 *
 * Результатом теста должно быть следующее: размер файла, время записи и время чтения.
 * Время считать через System.currentTimeMillis().
 * В итоговом пулРеквесте должна быть информация об этих значениях для каждого теста. (всего 4 теста,
 * за каждый тест 1 балл)  и 3 балла за правильное объяснение результатов
 * Для тестов создайте класс в соответствующем пакете в папке тестов. Используйте другие тесты - как примеры.
 *
 * В конце теста по чтению данных, не забывайте удалять файлы
 */
public class Serializer {

    /**
     * 1 тугрик
     * Реализовать простую сериализацию, с помощью специального потока для сериализации объектов
     * @param animals Список животных для сериализации
     * @param fileName файл в который "пишем" животных
     */
    public void defaultSerialize(List<Animal> animals, String fileName) {
        Path file = Paths.get(fileName);
        if (Files.notExists(file)) {
            return;
        }
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(file))) {
            for (Animal animal : animals) {
                objectOutputStream.writeObject(animal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1 тугрик
     * Реализовать простую дисериализацию, с помощью специального потока для дисериализации объектов
     *
     * @param fileName файл из которого "читаем" животных
     * @return список животных
     */
    public List<Animal> defaultDeserialize(String fileName) {
        Path file = Paths.get(fileName);
        if (Files.notExists(file)) {
            return Collections.emptyList();
        }
        List<Animal> animals = new ArrayList<>();
        try (InputStream inputStream = Files.newInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            while (inputStream.available() > 0) {
                animals.add((Animal) objectInputStream.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return animals;
    }


    /**
     * 1 тугрик
     * Реализовать простую ручную сериализацию, с помощью специального потока для сериализации объектов и специальных методов
     * @param animals Список животных для сериализации
     * @param fileName файл в который "пишем" животных
     */
    public void serializeWithMethods(List<AnimalWithMethods> animals, String fileName) {
        Path file = Paths.get(fileName);
        if (Files.notExists(file)) {
            return;
        }
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(file))) {
            for (AnimalWithMethods animal : animals) {
                objectOutputStream.writeObject(animal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1 тугрик
     * Реализовать простую ручную дисериализацию, с помощью специального потока для дисериализации объектов
     * и специальных методов
     *
     * @param fileName файл из которого "читаем" животных
     * @return список животных
     */
    public List<AnimalWithMethods> deserializeWithMethods(String fileName) {
        Path file = Paths.get(fileName);
        if (Files.notExists(file)) {
            return Collections.emptyList();
        }
        List<AnimalWithMethods> animals = new ArrayList<>();
        try (InputStream inputStream = Files.newInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            while (inputStream.available() > 0) {
                animals.add((AnimalWithMethods) objectInputStream.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return animals;
    }

    /**
     * 1 тугрик
     * Реализовать простую ручную сериализацию, с помощью специального потока для сериализации объектов и интерфейса Externalizable
     * @param animals Список животных для сериализации
     * @param fileName файл в который "пишем" животных
     */
    public void serializeWithExternalizable(List<AnimalExternalizable> animals, String fileName) {
        Path file = Paths.get(fileName);
        if (Files.notExists(file)) {
            return;
        }
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(file))) {
            for (AnimalExternalizable animal : animals) {
                objectOutputStream.writeObject(animal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1 тугрик
     * Реализовать простую ручную дисериализацию, с помощью специального потока для дисериализации объектов
     * и интерфейса Externalizable
     *
     * @param fileName файл из которого "читаем" животных
     * @return список животных
     */
    public List<AnimalExternalizable> deserializeWithExternalizable(String fileName) {
        Path file = Paths.get(fileName);
        if (Files.notExists(file)) {
            return Collections.emptyList();
        }
        List<AnimalExternalizable> animals = new ArrayList<>();
        try (InputStream inputStream = Files.newInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            while (inputStream.available() > 0) {
                animals.add((AnimalExternalizable) objectInputStream.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return animals;
    }

    /**
     * 2 тугрика
     * Реализовать ручную сериализацию, с помощью высокоуровневых потоков. Сами ручками пишем поля,
     * без использования методов writeObject
     *
     * @param animals  Список животных для сериализации
     * @param fileName файл, в который "пишем" животных
     */
    public void customSerialize(List<Animal> animals, String fileName) {
        Path file = Paths.get(fileName);
        if (Files.notExists(file)) {
            return;
        }
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(file))) {
            for (Animal animal : animals) {
                if (animal == null) {
                    objectOutputStream.writeByte(0);
                    continue;
                }
                objectOutputStream.writeByte(1);
                if (animal.getName() == null) {
                    objectOutputStream.writeByte(0);
                } else {
                    objectOutputStream.writeByte(1);
                    objectOutputStream.writeUTF(animal.getName());
                }
                objectOutputStream.writeInt(animal.getAge());
                objectOutputStream.writeDouble(animal.getWeight());
                objectOutputStream.writeByte(animal.isVegetarian() ? 1 : 0);
                AnimalType animalType = animal.getAnimalType();
                if (animalType == null) {
                    objectOutputStream.writeByte(0);
                } else {
                    objectOutputStream.writeByte(1);
                    objectOutputStream.writeUTF(animalType.name());
                }
                Worker overseer = animal.getOverseer();
                if (overseer == null) {
                    objectOutputStream.writeByte(0);
                } else {
                    objectOutputStream.writeByte(1);
                    if (overseer.getSurname() == null) {
                        objectOutputStream.writeByte(0);
                    } else {
                        objectOutputStream.writeByte(1);
                        objectOutputStream.writeUTF(overseer.getSurname());
                    }
                    objectOutputStream.writeLong(overseer.getId());
                    objectOutputStream.writeByte(overseer.isOnVacation() ? 1 : 0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2 тугрика
     * Реализовать ручную дисериализацию, с помощью высокоуровневых потоков. Сами ручками читаем поля,
     * без использования методов readObject
     *
     * @param fileName файл из которого "читаем" животных
     * @return список животных
     */
    public List<Animal> customDeserialize(String fileName) {
        Path file = Paths.get(fileName);
        if (Files.notExists(file)) {
            return Collections.emptyList();
        }
        List<Animal> animals = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(file))) {
            while (objectInputStream.available() > 0) {
                if (objectInputStream.readByte() == 0) {
                    animals.add(null);
                    continue;
                }
                String name = null;
                if (objectInputStream.readByte() == 1) {
                    name = objectInputStream.readUTF();
                }
                int age = objectInputStream.readInt();
                double weight = objectInputStream.readDouble();
                boolean isVegetarian = (objectInputStream.readByte() == 1);
                AnimalType animalType = null;
                if (objectInputStream.readByte() == 1) {
                    animalType = AnimalType.valueOf(objectInputStream.readUTF());
                }
                Worker overseer = null;
                if (objectInputStream.readByte() == 1) {
                    String surname = null;
                    if (objectInputStream.readByte() == 1) {
                        surname = objectInputStream.readUTF();
                    }
                    long id = objectInputStream.readLong();
                    boolean isOnVacation = (objectInputStream.readByte() == 1);
                    overseer = new Worker(surname, id, isOnVacation);
                }
                animals.add(new Animal(name, age, weight, isVegetarian, animalType, overseer));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return animals;
    }
}

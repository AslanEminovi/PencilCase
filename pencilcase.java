import java.util.Scanner;

class Slot {
    private int capacity;
    private int pencils;

    public Slot(int capacity) {
        this.capacity = capacity;
        this.pencils = 0;
    }

    public boolean isSlotOpen() {
        return pencils < capacity;
    }

    public void addPencils(int numPencils) {
        if (!isSlotOpen()) {
            System.out.println("The slot is full. Cannot add more pencils.");
            return;
        }
        int addedPencils = Math.min(numPencils, capacity - pencils);
        pencils += addedPencils;
        System.out.println(addedPencils + " pencil(s) added to the slot.");
    }

    public void removePencils(int numPencils) {
        int removedPencils = Math.min(numPencils, pencils);
        pencils -= removedPencils;
        System.out.println(removedPencils + " pencil(s) removed from the slot.");
    }

    public int getPencilCount() {
        return pencils;
    }
}

public class pencilcase {
    private Slot[] slots;

    public pencilcase(int slotCount, int[] slotCapacities) {
        slots = new Slot[slotCount];
        for (int i = 0; i < slotCount; i++) {
            slots[i] = new Slot(slotCapacities[i]);
        }
    }

    public void unzip() {
        for (Slot slot : slots) {
            slot.addPencils(slot.getPencilCount());
        }
        System.out.println("Pencil case unzipped.");
    }

    public int getTotalPencilCount() {
        int totalPencils = 0;
        for (Slot slot : slots) {
            totalPencils += slot.getPencilCount();
        }
        return totalPencils;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Pencil Case");

        System.out.print("Enter the number of slots in the pencil case: ");
        int slotCount = scanner.nextInt();
        int[] slotCapacities = new int[slotCount];

        for (int i = 0; i < slotCount; i++) {
            System.out.print("Enter the maximum capacity for slot " + (i + 1) + ": ");
            slotCapacities[i] = scanner.nextInt();
        }

        pencilcase pencilcase = new pencilcase(slotCount, slotCapacities);

        while (true) {
            System.out.println("What do you want to do with the pencil case: ");
            System.out.println("1. Unzip pencil case");
            System.out.println("2. Add Pencils to a Slot");
            System.out.println("3. Remove Pencils from a Slot");
            System.out.println("4. Check Total Pencil Count");
            System.out.println("5. Quit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    pencilcase.unzip();
                    break;
                case 2:
                    System.out.print("Enter the slot number to add pencils: ");
                    int slotNumber = scanner.nextInt();
                    if (slotNumber > 0 && slotNumber <= slotCount) {
                        System.out.print("Enter the number of pencils to add: ");
                        int pencilsToAdd = scanner.nextInt();
                        pencilcase.slots[slotNumber - 1].addPencils(pencilsToAdd);
                    } else {
                        System.out.println("Invalid slot number.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the slot number to remove pencils: ");
                    int slotToRemoveFrom = scanner.nextInt();
                    if (slotToRemoveFrom > 0 && slotToRemoveFrom <= slotCount) {
                        System.out.print("Enter the number of pencils to remove: ");
                        int pencilsToRemove = scanner.nextInt();
                        pencilcase.slots[slotToRemoveFrom - 1].removePencils(pencilsToRemove);
                    } else {
                        System.out.println("Invalid slot number.");
                    }
                    break;
                case 4:
                    System.out.println("Total pencils in the pencil case: " + pencilcase.getTotalPencilCount());
                    break;
                case 5:
                    System.out.println("Bye");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }
}

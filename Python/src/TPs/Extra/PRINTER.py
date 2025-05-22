import random
from Blueprints.Data_Structures.Queue import Queue

class Printer:
    """
    The Printer class will need to track whether it has a current task. If it does, then it is busy
    and the amount of time needed can be computed from the number of pages in the task.
    The constructor will also allow the pages-per-minute setting to be initialized.
    The tick method decrements the internal timer and sets the printer to idle if the task is completed.
    """
    def __init__(self, ppm: int):
        self.rate = ppm
        self.task = None
        self.time = 0

    def tick(self):
        if self.task is not None:
            self.time -= 1
            if self.time <= 0:
                self.task = None

    def busy(self) -> bool:
        if self.task is not None:
            return True
        else:
            return False

    def start_next(self, new: 'Task'):
        self.task = new
        self.time = new.pages * 60 / self.rate

class Task:
    """
    The Task class will represent a single printing task.
    When the task is created, a random number generator will provide a length from 1 to 20 pages.
    We have chosen to use the randrange function from the random module.
    """
    def __init__(self, time: int):
        self._timestamp = time
        self._pages = random.randrange(1, 21)

    @property
    def timestamp(self) -> int:
        return self._timestamp

    @property
    def pages(self) -> int:
        return self._pages

    def wait_time(self, current_time: int) -> int:
        return current_time - self.timestamp

def simulation(seconds: int, ppm: int):
    """
    1. Create a queue of print tasks. Each task will be given a timestamp upon its arrival. The queue is empty to start.
    2. For each second (current_second):
    • Does a new print task get created? If so, add it to the queue with the current_second as the timestamp.
    • If the printer is not busy and if a task is waiting,
    – Remove the next task from the print queue and assign it to the printer.
    – Subtract the timestamp from the current_second to compute the waiting time for that task.
    – Append the waiting time for that task to a list for later processing.
    – Based on the number of pages in the print task, figure out how much time will be required.
    • The printer now does one second of printing if necessary. It also subtracts one second from the time required for that task.
    • If the task has been completed, in other words, the time required has reached zero, the printer is no longer busy.
    3. After the simulation is complete, compute the average waiting time from the list of waiting times generated.
    """
    printer = Printer(ppm)
    print_queue = Queue()
    waiting_times = []

    for curr in range(seconds):
        if new_task():
            task = Task(curr)
            print_queue.enqueue(task)

        if (not printer.busy()) and (not print_queue.is_empty()):
            next = print_queue.dequeue()
            waiting_times.append(next.wait_time(curr))
            printer.start_next(next)

        printer.tick()

    average = sum(waiting_times) / len(waiting_times)
    print("Average Wait: %6.2f secs, %3d tasks remaining." % (average, print_queue.size()))

def new_task():
    num = random.randrange(1, 181)

    if num == 180:
        return True
    else:
        return False

for i in range(10):
    simulation(3600, 5)
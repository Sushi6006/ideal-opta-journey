# Generator for locations
import csv
from math import sqrt
import random
from random import randint

class Generator:

    # code for each type
    __TYPE_CODE  = {"turbine": 1,
                  "vessel": 2,
                  "base": 3,
                  "technician": 4}
    
    # count of each type
    __TYPE_COUNT = {"turbine": 8,
                    "vessel": 5,
                    "base": 1,
                    "technician": 0,
                    "location": 9}
    
    # demands
    __DEMAND_TYPE = ["mechanical", "electrical"]

    # constructor
    def __init__(self, type_code: dict = None, type_count: dict = None):
        if type_code:
            this.TYPE_CODE = type_code
        if type_count:
            this.TYPE_COUNT = type_count

    def generate_files(self):
        self.generate_sizes()
        turbine_list = self.generate_turbines()
        base_list = self.generate_bases()
        vessel_list = self.generate_vessels(base_list)
        location_list = self.generate_locations(turbine_list, base_list)
        self.generate_distance_map(location_list)

    def generate_sizes(self):
        # generate content
        content = ""
        for name, count in self.__TYPE_COUNT.items():
            content += f'{name},{count}\n'

        # write to file
        with open("sizes.csv", "w") as size_file:
            size_file.write(content[:-1])

    def generate_turbines(self) -> list:
        
        # generate data
        data = []
        turbine_list = []
        for i in range(self.__TYPE_COUNT["turbine"]):
            turbine = f'{self.__TYPE_CODE["turbine"]}{i}'
            turbine_list.append(turbine)
            entry = [turbine,                            # turbine code
                     randint(1, 100),                    # demand ammount
                     random.choice(self.__DEMAND_TYPE)]  # demand type
            data.append(entry)
        
        # write to file
        with open("turbine.csv", "w") as turbine_file:
            writer = csv.writer(turbine_file)
            writer.writerows(data)

        return turbine_list

    def generate_bases(self) -> list:
        # generate data
        base_list = []
        for i in range(self.__TYPE_COUNT["base"]):
            base_list.append(f'{self.__TYPE_CODE["base"]}{i}')
        
        # write to file
        with open("base.csv", "w") as base_file:
            base_file.write("\n".join(base_list))
        
        return base_list

    def generate_vessels(self, base_list: list) -> list:

        # generate data
        data = []
        vessel_list = []
        for i in range(self.__TYPE_COUNT["vessel"]):
            vessel = f'{self.__TYPE_CODE["vessel"]}{i}'
            vessel_list.append(vessel)
            entry = [vessel,                    # vessel code
                     randint(1, 100),           # capacity
                     random.choice(base_list)]  # base
            data.append(entry)
        
        # write to file
        with open("vessel.csv", "w") as vessel_file:
            writer = csv.writer(vessel_file)
            writer.writerows(data)

        return vessel_list

    def generate_locations(self, turbine_list: list, base_list: list) -> list:

        # generate data
        location_list = []  # list instead of dict to remain the order
                            # (location_code, (x, y))
        data = []
        for location in turbine_list + base_list:
            coord = (randint(-100, 100), randint(-100, 100))
            location_list.append((location, coord))
            entry = [location, coord[0], coord[1]]
            data.append(entry)
        
        # write to file
        with open("location.csv", "w") as location_file:
            writer = csv.writer(location_file)
            writer.writerows(data)

        return location_list
    
    def generate_distance_map(self, location_list: list):

        # calculate distance between two tuples with (x, y)
        def distance_between(coord_a: tuple, coord_b: tuple) -> int:
            return sqrt(sum([abs(coord_a[i] - coord_b[i]) ** 2 for i in range(len(coord_a))]))

        # generate data
        location_count = len(location_list)
        data = []
        for i in range(location_count):
            row_data = []
            for j in range(location_count):
                row_data.append(distance_between(location_list[i][1], location_list[j][1]))
            data.append(row_data)

        # write to file
        with open("distancemap.csv", "w") as distance_file:
            writer = csv.writer(distance_file)
            writer.writerows(data)


def main():

    # create a generator
    generator = Generator()
    
    # generate files
    generator.generate_files()


if __name__ == "__main__":
    main()
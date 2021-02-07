# Source Code

- `output.txt` is output, including Log
- `src/main/java/com/windfarmplanner/`
  - `data` is how we generate random data, **not project related**
  - `location` is location related classes
  - `solver` is some of Opta core related stuff
  - `timewindowed` is timewindowed classes
  - `AbstractPersistable.java` is dont-need-to-know
  - `App.java` is main app, used to run
  - `RoutingSolution.java` is the problem and the solution class
  - `WindfarmDataImporter.java` is used to import data but i think it is not finished yet
  - `ScoreCalculator.java` is used to calculate score, using easy java
  - the rest is pretty self-explanatory
- `src/resources/`
  - `config.xml` is solver configuration
- `src/srcchecklist.md` is to check what needs to be done
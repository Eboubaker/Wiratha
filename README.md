```
git clone https://github.com/Eboubaker/Wiratha
cd Wiratha
.\gradlew run
```
Family members codes:
```
"1" -> Husband
"2" -> Son
"3" -> SonOfSon
"4" -> Father
"5" -> GrandFather
"6" -> Brother
"7" -> BrotherFromFather
"8" -> BrotherFromMother
"`1" -> Wife
"`2" -> Daughter
"`3" -> DaughterOfSon
"`4" -> Mother
"`5" -> GrandMotherFromFather
"`6" -> GrandMotherFromMother
"`7" -> Sister
"`8" -> SisterFromFather
"`9" -> SisterFromMother
"#1" -> SonOfBrother
"#2" -> SonOfBrotherFromFather
"#3" -> BrotherOfFather
"#4" -> BrotherOfFatherFromFather
"#5" -> SonOfBrotherOfFather
"#6" -> SonOfBrotherOfFatherFromFather
```

Examples:
- Husband died and left Wife 1 sister and 2 daughters
```
family: `1`7`2`2
_________________________
| heir    | share| share%|
|========================|
| Wife    | 3/24 | 12.5% |
| Sister  | 5/24 | 20.83%|
| Daughter| 8/24 | 33.33%|
| Daughter| 8/24 | 33.33%|
```
- Wife died and left Husband 2 sons and mother
```
family: 122`4
________________________
| heir   | share| share%|
|=======================|
| Husband| 6/24 | 25%   |
| Son    | 7/24 | 29.16%|
| Son    | 7/24 | 29.16%|
| Mother | 4/24 | 16.66%|
```

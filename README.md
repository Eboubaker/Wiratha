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
"7" -> BrotherFromFather (not the same mother)
"8" -> BrotherFromMother (not the same father)
"`1" -> Wife
"`2" -> Daughter
"`3" -> DaughterOfSon
"`4" -> Mother
"`5" -> GrandMotherFromFather (mother of father)
"`6" -> GrandMotherFromMother (mother of mother)
"`7" -> Sister
"`8" -> SisterFromFather (not the same mother)
"`9" -> SisterFromMother (not the same father)
"#1" -> SonOfBrother
"#2" -> SonOfBrotherFromFather (son of 7)
"#3" -> BrotherOfFather (uncle)
"#4" -> BrotherOfFatherFromFather (uncle and father don't share the same mother)
"#5" -> SonOfBrotherOfFather (son of #3)
"#6" -> SonOfBrotherOfFatherFromFather (son of #4)
```
> all members are relative to the one who died (Husband or Wife), for example `` `7`` is sister of Husband if Husband is the heir, and is sister of Wife if the Wife is the heir.
 
> any member that is not listed will always get 0 for example SonOfDaughter


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
- Husband died and left Wife 1 sister a son and 2 daughters
```
family: `1`7`2`22
_________________________
| heir    | share| share%|
|========================|
| Son     | 14/32| 43.75%|
| Daughter| 7/32 | 21.87%|
| Daughter| 7/32 | 21.87%|
| Wife    | 4/32 | 12.5% |
| Sister  | 0/32 | 0%    |
```

#### Reference used.  
![image](https://user-images.githubusercontent.com/37766821/159339990-6993ce9b-cbc7-43c2-842a-d4e7038cde06.png)


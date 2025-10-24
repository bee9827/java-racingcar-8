# java-racingcar-precourse

## 기능 목록
### 입력 
- 경주할 자동차 이름 
    - 이름은 쉼표로 구분한다.  

```
경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)  
pobi,woni,jun
```

- 몇 번의 이동을 할 것인지 입력한다.

```
시도할 횟수는 몇 회인가요?  
5
```

### 비즈니스
#### 자동차 관리
-[x] 이름을 관리한다.
    - 이름은 1~5사이의 문자만 가능하다. 
    - 영어, 한글, 숫자, 문자 "-" 까지만 허용한다. 
-[x] 위치를 관리한다. 
    - 0~9 사이의 값을 받으며, 무작위 값이 4 이상일 경우 전진한다.

## 랜덤값 생성
-[x] 무작위 값을 생성한다
    - 값의 범위는 0~9 이다.

#### 게임 관리
-[x] 자동차들을 관리한다.
-[x] n대의 자동차는 주어진 횟수동안 전진하거나 멈춘다.
    - 랜덤값 생성기를 이용하여 0~9 사이의 값을 전달한다. 
-[x] 우승자를 반환한다.
    - 우승자는 한 명 이상일 수 있다.
    - 우승자가 여러 명일 경우 쉼표`,`를 이용하여 구분한다.

### 출력
- 각 횟수마다 자동차들의 정보를 출력한다.

```
실행 결과
pobi : -
woni : 
jun : -

pobi : --
woni : -
jun : --

pobi : ---
woni : --
jun : ---

pobi : ----
woni : ---
jun : ----

pobi : -----
woni : ----
jun : ------

```
- 우승자의 정보를 출력한다.

```
최종 우승자 : pobi, jun
```

    
사용자가 잘못된 값을 입력할 경우 IllegalArgumentException을 발생시킨 후 애플리케이션은 종료되어야 한다.


## 구현 시 고민한 것들

### 랜덤값 생성 객체 왜 인터페이스로 구현했나?
`RacingGame` -> `RacingCar` 랜덤값을 주입해줍니다.  
이때 `interface RandomValueGenerator::generate`를 사용합니다.

이렇게 설계 하게 된 이유는 **랜덤값**은 테스트가 어렵기 때문입니다.
자동차를 관리할 책임이 있는 `RacingCar` 는 기능에 대한 보장이 필요했습니다.
따라서 랜덤값을 사용하지 않고 구성하였고 이에 따라서 값을 주입해줄 객체가 필요했습니다.

만약 static 함수를 쓰게 될경우 값을 바꿔치기 하기가 어렵다고 생각했습니다.(테스트가 어렵다)
하지만 `interface`를 이용해서 **랜덤값 생성 객체**를 주입을 받는다면 테스트에서 바꿔치기 할 수 있기 때문에 `interface`를 사용하게 되었습니다.


### `RacingGame::moves` 인자

```java
public class RacingGame {
    private final List<RacingCar> racingCars;
    private final RandomValueGenerator randomValueGenerator;

  public RacingGame(List<RacingCar> racingCars, RandomValueGenerator randomValueGenerator) {
    this.racingCars = racingCars;
    this.randomValueGenerator = randomValueGenerator;
  }
  
  public List<RacingCarDto> moves() {
    racingCars.forEach(racingCar ->
            racingCar.move(randomValueGenerator.generate()));
    ...
  }
  
  ...
}
```
저는 위와 같이 생성자에서 주입을 받아서 랜덤값 생성기를 이용 했습니다.
이렇게 구현할 경우 사용이 간편하지만, 테스트가 문제였습니다.

`moves()`외에는 해당 객체를 사용하지 않습니다.
하지만 테스트를 진행하기 위해서는 `RandomValueGenerator`를 만들고 주입을 해줘야 했습니다.
이것이 왜 문제가 되냐면 랜덤값 생성이 필요하지 않은 `getWinners()`를 호출 할 때도 생성해줘야 하기 때문입니다.
더 많은 함수가 선언된다면 해결하기 힘들다고 생각했습니다.

```java
public class RacingGame {
    private final List<RacingCar> racingCars;

    public RacingGame(List<RacingCar> racingCars) {
        this.racingCars = racingCars;
    }

    public List<RacingCarDto> moves(final RandomValueGenerator randomValueGenerator) {
        racingCars.forEach(racingCar ->
                racingCar.move(randomValueGenerator.generate()));

        ...
    }
    
    ...
}
```
위와 같은 방법이 괜찮은 방법인지, 그리고 다른 대안이 있다면 어떤게 있는지 알려주시면 감사하겠습니다.




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
    - 이름은 1~5사이의 문자이다.
    - 영어, 한글, 숫자, 문자 "-" 로 구성된다.
-[x] 위치를 관리한다.
    - 0~9 사이의 값을 받으며, 무작위 값이 4 이상일 경우 전진한다.

예외 목록

| 분야          |   예시   | 이유                 |
  |-------------|:------:|:-------------------|
| 이름(변수)      | javaji | 글자수 초과             |
| 이름(변수)      |        | 공백                 |
| 이름(변수)      |   \\   | 허용되지 않은 형식         |
| 위치(변수)      |   -1   | 음수는 허용하지 않는다       |
| 위치 이동 매개 변수 |   10   | 매개변수 값의 범위는 0~9 이다 |

## 랜덤값 생성

-[x] 무작위 값을 생성한다
    - 값의 범위는 0~9 이다.

#### 게임 관리

-[x] 자동차들을 관리한다.
    - 중복된 이름의 자동차는 불가능하다.
-[x] n대의 자동차는 주어진 횟수동안 전진하거나 멈춘다.
    - 랜덤값 생성기를 이용하여 0~9 사이의 값을 전달한다.
-[x] 우승자를 반환한다.
    - 우승자는 한 명 이상일 수 있다.
    - 우승자가 여러 명일 경우 쉼표`,`를 이용하여 구분한다.

예외 목록

| 분야 |     예시     | 이유     |
  |----|:----------:|:-------|
| 이름 | yong, yong | 중복된 이름 |

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

`RacingGame`에서 `RacingCar::move`에 랜덤값을 주입해줍니다.  
이때 `interface RandomValueGenerator::generate`를 사용합니다.

이렇게 설계 하게 된 이유는 **랜덤값**은 테스트가 어렵기 때문입니다.
자동차를 관리할 책임이 있는 `RacingCar` 는 기능에 대한 보장이 필요했습니다.
따라서 랜덤값을 사용하지 않고 구성하였고 이에 따라서 값을 주입해줄 객체가 필요했습니다.

#### 랜덤값을 static 함수로 받아오면 되지 않나요?

만약 static 함수를 쓰게 될경우 테스트가 어렵다고 생각했습니다.
하지만 `interface`를 이용해서 **랜덤값 생성 객체**를 주입을 받는다면 테스트에서 바꿔치기 할 수 있기 때문에 테스트가 쉬워집니다.

### `RandomValueGenerator` 는 관리해야 하는 객체인가?

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
이렇게 구현할 경우 사용이 간편하지만, 두가지 문제가 있었습니다.

1. RandomValueGenerator를 관리하는것이 RacingGame의 책임인가?
2. moves() 함수 외에는 해당 객체를 사용하지 않는다.

어려운 문제는 일단 놔두고 테스트를 생각해 보았습니다.
테스트를 진행하기 위해서는 RacingGame을 생성하는 모든 시점에서 `RandomValueGenerator`를 만들고 주입을 해줘야 합니다.
하지만 이는 불필요한 일입니다. 또한 테스트를 할때 각 횟수마다 다른 테스트를 하기 위해서는 객체를 주입해주는 것이 훨씬 좋은 설계라고 느꼈습니다.

RandomValueGenerator 의 관리 책임은 RacingCarGame 에 있지 않다고 생각 했고, 아래와 같이 구성했습니다.

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

### 예외처리 문구 관리

저는 무분별하게 흩어져 있는 예외 문구를 관리할 필요가 있다고 생각했습니다.
왜냐하면 예외 문구는 사용자에게 직접 보여져야 하는 것이기 때문입니다.

함께 나누기에 있던 [예외 팩토리 패턴](https://youngi2.tistory.com/14) 을 이용하여 예외처리 문구를 관리하게 되었습니다. 하지만 한가지 문제가 있습니다.

사용자가 잘못된 입력을 해서 발생하는 예외와, 코딩을 잘못해서 발생하는 예외.
이 두가지 예외를 구분해야 했습니다.

저는 각 예외를 `IllegalArgumentException`를 상속한 `RacingCarException`,`IllegalStateException`을 상속한 `RacingCarStateException` 을 이용해서 구분했습니다.


 


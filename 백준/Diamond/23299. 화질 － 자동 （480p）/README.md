# [Diamond IV] 화질 - 자동 (480p) - 23299 

[문제 링크](https://www.acmicpc.net/problem/23299) 

### 성능 요약

메모리: 42232 KB, 시간: 248 ms

### 분류

자료 구조, 분리 집합, 분할 정복, 다이나믹 프로그래밍, 그래프 이론, 배낭 문제, 오프라인 동적 연결성 판정, 오프라인 쿼리

### 제출 일자

2024년 8월 21일 14:20:30

### 문제 설명

<p>상민이는 하루 일과를 끝내고 저녁에 너튜브를 보는 게 삶의 유일한 낙이다. 그러나 저녁 시간에는 다른 사람들도 너튜브를 많이 보기 때문에 너튜브 서버의 네트워크 대역폭이 부족한 날엔 종종 영상이 끊기곤 했다. 이를 참을 수 없던 상민이는 문제를 해결하기 위해 너튜브에 입사해 다음과 같은 알고리즘을 만들어냈다.</p>

<ol>
	<li>너튜브에서는 6종류의 <strong>화질 옵션</strong>을 제공한다.</li>
	<li>각 화질 옵션마다 재생 시 차지하는 일정량의 <strong>네트워크 대역폭</strong>이 있다.</li>
	<li>모든 시청자에게서 화질 옵션마다 느끼는 <strong>만족도</strong>를 수집한다.</li>
	<li>매 분마다 시청 중인 시청자들의 <strong>만족도의 합이 최대</strong>가 되도록 시청자들의 화질 옵션을 조정한다. 이때 시청자들이 사용하는 네트워크 대역폭의 합이 <strong>너튜브 서버의 네트워크 대역폭</strong>을 <strong>초과</strong>하는 일이 없도록 조정한다.</li>
</ol>

<p><strong>화질 옵션</strong>은 화질이 높은 순서대로 8K, 4K, FHD, HD, 480p, 240p가 있다. 즉, <strong>가장 높은</strong> 화질 옵션은 <strong>8K</strong>, <strong>가장 낮은</strong> 화질 옵션은 <strong>240p</strong>다. 높은 화질 옵션은 더 많은 네트워크 대역폭을 차지하고, 낮은 화질 옵션은 적은 네트워크 대역폭을 차지한다. 만약 어떤 시청자가 서버의 네트워크 대역폭이 부족해 어떠한 화질 옵션으로도 시청하지 못하게 된다면 해당 시청자는 네트워크 대역폭을 차지하지 않으며, 느끼는 만족도 역시 0이다.</p>

<p>예를 들어, 너튜브 서버의 네트워크 대역폭이 20이고, 시청자 A와 B의 화질 옵션별 사용하는 대역폭과 수집한 만족도가 아래와 같다고 하자.</p>

<table class="table table-bordered table-center-40 th-center td-center">
	<thead>
		<tr>
			<th>화질 옵션</th>
			<th>8K</th>
			<th>4K</th>
			<th>FHD</th>
			<th>HD</th>
			<th>480p</th>
			<th>240p</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th>사용 대역폭</th>
			<td>13</td>
			<td>11</td>
			<td>7</td>
			<td>5</td>
			<td>3</td>
			<td>2</td>
		</tr>
	</tbody>
</table>

<p style="text-align: center;"><표 1> 화질 옵션별 사용 네트워크 대역폭 예시</p>

<table class="table table-bordered table-center-40 th-center td-center">
	<thead>
		<tr>
			<th>화질 옵션</th>
			<th>8K</th>
			<th>4K</th>
			<th>FHD</th>
			<th>HD</th>
			<th>480p</th>
			<th>240p</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th>A</th>
			<td>32</td>
			<td>31</td>
			<td>21</td>
			<td>9</td>
			<td>4</td>
			<td>1</td>
		</tr>
		<tr>
			<th>B</th>
			<td>10</td>
			<td>9</td>
			<td>8</td>
			<td>7</td>
			<td>6</td>
			<td>5</td>
		</tr>
	</tbody>
</table>

<p style="text-align: center;"><표 2> 시청자의 화질 옵션별 만족도 예시</p>

<p>A는 0분에 시청을 시작해 5분에 끝내고, B는 3분에 시청을 시작해 8분에 끝낸다고 가정하자.</p>

<p>A는 0분부터 3분까지는 8K로 시청할 수 있다. 그러나 3분부터 5분까지는 B와 동시에 8K로 시청하면 서버의 네트워크 대역폭을 초과하게 되기 때문에 시청자의 화질 옵션을 낮추어야 한다.</p>

<p>상민이는 시청자의 만족도를 중요시하기 때문에 모든 시청자의 만족도의 합을 최대화 할 수 있도록 화질 옵션을 조정할 것이다. 그렇기 때문에 3분부터 5분까지는 화질 옵션을 낮추어도 만족도가 적게 줄어드는 시청자 B의 화질을 FHD로 낮춘다. 이후 5분에 A의 시청이 종료되면 B의 화질 옵션을 높여 B는 5분부터 8분까지 8K로 시청할 수 있다.</p>

<p>만족도는 <strong>분 단위로 계산</strong>하기 때문에, A는 32 × 5분 = 160, B는 8 × 2분 + 10 × 3분 = 46으로 총 206의 만족도를 얻는다.</p>

<p>알고리즘을 적용한 뒤, 상민이는 시청자들이 변화를 체감할 수 있을지 궁금하다. 상민이를 위해 모든 시청자들의 <strong>만족도의 합</strong>을 계산해주자.</p>

### 입력 

 <p>첫 번째 줄에 모든 시청자의 수 <em>N</em>과 너튜브 서버의 네트워크 대역폭 <em>W</em>가 주어진다. (1 ≤ <em>N</em> ≤ 500, 1 ≤ <em>W</em> ≤ 5,000)</p>

<p>두 번째 줄에 화질 옵션 별로 각 시청자가 사용하는 대역폭 <em>Q<sub>8K</sub></em>, <em>Q<sub>4K</sub></em>, <em>Q<sub>FHD</sub></em>, <em>Q<sub>HD</sub></em>, <em>Q<sub>480p</sub></em>, <em>Q<sub>240p</sub></em>가 주어진다. (1 ≤ <em>Q<sub>240p</sub></em> ≤ <em>Q<sub>480p</sub></em> ≤ <em>Q<sub>HD</sub></em> ≤ <em>Q<sub>FHD</sub></em> ≤ <em>Q<sub>4K</sub></em> ≤ <em>Q<sub>8K</sub></em> ≤ <em>W</em>)</p>

<p>세 번째 줄부터 <em>N + 1</em>번째 줄까지 시청을 시작 시간 <i>S</i>, 시청을 끝내는 시간 <em>E</em>, 화질 옵션별 만족도 <em>P<sub>8K</sub></em>, <em>P<sub>4K</sub></em>, <em>P<sub>FHD</sub></em>, <em>P<sub>HD</sub></em>, <em>P<sub>480p</sub></em>, <em>P<sub>240p</sub></em>가 주어진다. (1 ≤ <em>S</em> < <i>E</i> ≤ 1,000,000,000, 1 ≤ <em>P<sub>240p</sub></em> ≤ <em>P<sub>480p</sub></em> ≤ <em>P<sub>HD</sub></em> ≤ <em>P<sub>FHD</sub></em> ≤ <em>P<sub>4K</sub></em> ≤ <em>P<sub>8K</sub></em> ≤ 1,000)</p>

### 출력 

 <p>상민이의 알고리즘을 적용했을 때, 모든 시청자들의 만족도의 합을 출력한다.</p>


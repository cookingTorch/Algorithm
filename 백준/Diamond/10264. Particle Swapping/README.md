# [Diamond IV] Particle Swapping - 10264 

[문제 링크](https://www.acmicpc.net/problem/10264) 

### 성능 요약

메모리: 133556 KB, 시간: 2632 ms

### 분류

이분 탐색, 자료 구조, 분리 집합, 그래프 이론, 최소 공통 조상, 최소 스패닝 트리, 병렬 이분 탐색, 트리

### 제출 일자

2025년 5월 17일 10:49:53

### 문제 설명

<p><img alt="" src="https://www.acmicpc.net/upload/images2/particles.png" style="float:right; height:268px; width:248px">The research team of prof. Feynmansson is preparing a new groundbreaking experiment in particle physics. On a special plate they have prepared a system consisting of a number of nodes connected via wires<sup>2</sup>. In the beginning of the experiment a pair of particles appears at two different nodes of the system: one normal particle of matter appears at some node A, and one corresponding particle of antimatter appears at some node B. The goal of the experiment is to swap these particles, i.e., to arrive at a state where the normal particle is at node B and the antiparticle is at node A. This state should be reached by a sequence of moves, where each move consists of transmitting one of the particles from its current location to a neighbouring node via a wire.</p>

<p>As you probably remember from popular science TV programmes, playing with matter and antimatter is usually not that safe. In particular, if particles of matter and antimatter get too close to each other, they will annihilate each other blowing up the whole experiment. Therefore, the research team would like to swap the locations of the particles in such a manner that the minimum Euclidean distance between them during the experiment is as large as possible. This minimum distance is called the safeness of the experiment. For simplicity, we assume that while a particle is transmitted via a wire we do not consider its location; in other words, the only risky moments during the experiment are when both particles are at some nodes. You may assume that it is always possible to swap the particles with positive safeness, that is, so that the particles are never placed at the same node during swapping.</p>

<p>Another catch is that the physicists do not know precisely where the particles will appear. They have made a list of potential pairs of initial locations (A, B), and for each of them they would like to know the maximum possible safeness of swapping the particles. Help them in this task.</p>

<p><sup>2</sup>The wires may cross each other on the plate.</p>

### 입력 

 <p>The first line of the input contains a single integer n (1 ≤ n ≤ 500), denoting the number of nodes in the system. Then follow n lines, each containing two integers x, y (−10 000 ≤ x, y ≤ 10 000); the numbers in the i-th line denote the coordinates on the plate of the i-th node. No two nodes are located at the same point.</p>

<p>The next line of the input contains a single integer m (0 ≤ m ≤ 2 000), denoting the number of wires in the system. Then follow m lines; each line contains a description of a wire as a pair of integers a, b (1 ≤ a, b ≤ n, a ≠ b), denoting the indices of the nodes that are connected by the wire. You may assume that no two nodes are connected by more than one wire, and no wire connects a node with itself.</p>

<p>The next line of the input contains a single integer <span style="color:rgb(37, 37, 37); font-family:sans-serif; font-size:14px">ℓ</span> (1 ≤ <span style="color:rgb(37, 37, 37); font-family:sans-serif; font-size:14px">ℓ</span> ≤ <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mrow><mjx-mo class="mjx-s3"><mjx-c class="mjx-c28 TEX-S3"></mjx-c></mjx-mo><mjx-mtable style="min-width: 0.6em;"><mjx-table><mjx-itable><mjx-mtr><mjx-mtd style="padding-bottom: 0.2em;"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D45B TEX-I"></mjx-c></mjx-mi><mjx-tstrut></mjx-tstrut></mjx-mtd></mjx-mtr><mjx-mtr><mjx-mtd style="padding-top: 0.2em;"><mjx-mn class="mjx-n"><mjx-c class="mjx-c32"></mjx-c></mjx-mn><mjx-tstrut></mjx-tstrut></mjx-mtd></mjx-mtr></mjx-itable></mjx-table></mjx-mtable><mjx-mo class="mjx-s3"><mjx-c class="mjx-c29 TEX-S3"></mjx-c></mjx-mo></mjx-mrow></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mrow data-mjx-texclass="INNER"><mo data-mjx-texclass="OPEN">(</mo><mtable columnspacing="1em" rowspacing="4pt"><mtr><mtd><mi>n</mi></mtd></mtr><mtr><mtd><mn>2</mn></mtd></mtr></mtable><mo data-mjx-texclass="CLOSE">)</mo></mrow></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">\(\begin{pmatrix}  n  \\  2  \end{pmatrix}\)</span></mjx-container>), denoting the length of the list of potential initial positions prepared by the physicists. Then follow <span style="color:rgb(37, 37, 37); font-family:sans-serif; font-size:14px">ℓ</span> lines, each containing two integers a, b (1 ≤ a, b ≤ n, a ≠ b), denoting the indices of the initial nodes A and B, respectively.</p>

### 출력 

 <p>Output exactly <span style="color:rgb(37, 37, 37); font-family:sans-serif; font-size:14px">ℓ</span> lines. The i-th line of the output should contain a single floating point number, being the maximum possible safeness for the i-th pair of initial positions listed by the physicists. Absolute or relative errors of value at most 10<sup>-6</sup> will be tolerated.</p>


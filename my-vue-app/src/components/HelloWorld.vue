<template>
  <div class="page-container">
    <!-- 초록색 상단 헤더 영역 -->
    <header class="header-section">
      <!-- 로고 -->
      <div class="logo">
        <h1>로고</h1>
      </div>
      <!-- 로그인 버튼 -->
      <button class="login-button">로그인</button>
    </header>

    <!-- 하얀색 하단 컨텐츠 영역 -->
    <main class="main-content-section">
      <!-- 막대그래프 영역 -->
      <div class="chart-container">
        <canvas ref="barChartCanvas" width="400" height="300"></canvas>
      </div>

      <!-- 사이트 설명글 영역 -->
      <div class="description-container">
        <h2>사이트 설명</h2>
        <p>
          이것은 사이트의 주요 기능을 설명하는 공간입니다. 사용자에게 제공되는
          가치와 정보를 간결하게 전달하세요.
        </p>
      </div>

      <!-- 메뉴 이동 버튼 영역 -->
      <div class="buttons-container">
        <button class="menu-button">메뉴 1</button>
        <button class="menu-button">메뉴 2</button>
        <button class="menu-button">메뉴 3</button>
      </div>
    </main>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import axios from 'axios';

const barChartCanvas = ref(null);

onMounted(() => {
  // 캔버스 요소를 가져옵니다.
  const canvas = barChartCanvas.value;
  if (!canvas) {
    console.error('Canvas element not found.');
    return;
  }
  const ctx = canvas.getContext('2d');

  // 차트 데이터 정의
  const data = {
    labels: ['데이터 A', '데이터 B', '데이터 C'],
    datasets: [
      {
        label: '데이터 1',
        values: [50, 85, 60],
        color: '#4CAF50', // 초록색
      },
      {
        label: '데이터 2',
        values: [70, 45, 90],
        color: '#2196F3', // 파란색
      },
    ],
  };

  const barWidth = 40;
  const barGap = 10;
  const groupGap = 50;
  const chartHeight = canvas.height - 40;
  const chartWidth = canvas.width - 40;
  const originX = 20;
  const originY = canvas.height - 20;

  // 축 그리기
  ctx.beginPath();
  ctx.moveTo(originX, originY);
  ctx.lineTo(originX, originY - chartHeight);
  ctx.moveTo(originX, originY);
  ctx.lineTo(originX + chartWidth, originY);
  ctx.strokeStyle = '#333';
  ctx.lineWidth = 2;
  ctx.stroke();

  // 바 차트 그리기
  let currentX = originX + groupGap / 2;
  data.labels.forEach((label, i) => {
    // 데이터 1의 막대
    const barHeight1 = (data.datasets[0].values[i] / 100) * chartHeight;
    ctx.fillStyle = data.datasets[0].color;
    ctx.fillRect(currentX, originY - barHeight1, barWidth, barHeight1);

    // 데이터 2의 막대
    const barHeight2 = (data.datasets[1].values[i] / 100) * chartHeight;
    ctx.fillStyle = data.datasets[1].color;
    ctx.fillRect(currentX + barWidth + barGap, originY - barHeight2, barWidth, barHeight2);

    // X축 레이블
    ctx.fillStyle = '#333';
    ctx.font = '14px Arial';
    ctx.textAlign = 'center';
    ctx.fillText(label, currentX + barWidth + barGap / 2, originY + 15);

    currentX += barWidth * 2 + barGap + groupGap;
  });

  // 범례 (Legend) 그리기
  ctx.textAlign = 'left';
  ctx.font = '12px Arial';
  ctx.fillStyle = '#333';
  const legendX = originX + 10;
  const legendY = 20;
  const rectSize = 10;

  ctx.fillStyle = data.datasets[0].color;
  ctx.fillRect(legendX, legendY, rectSize, rectSize);
  ctx.fillText(data.datasets[0].label, legendX + rectSize + 5, legendY + 8);

  ctx.fillStyle = data.datasets[1].color;
  ctx.fillRect(legendX + 80, legendY, rectSize, rectSize);
  ctx.fillText(data.datasets[1].label, legendX + 80 + rectSize + 5, legendY + 8);
});
</script>

<style scoped>
/* 전체 페이지 컨테이너 */
.page-container {
  font-family: Arial, sans-serif;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

/* 상단 헤더 영역 */
.header-section {
  background-color: #4CAF50;
  color: white;
  padding: 1rem 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
}

.logo h1 {
  margin: 0;
  font-size: 1.5rem;
}

.login-button {
  background-color: transparent;
  color: white;
  border: 2px solid white;
  padding: 0.5rem 1rem;
  border-radius: 20px;
  cursor: pointer;
  transition: background-color 0.3s, color 0.3s;
}

.login-button:hover {
  background-color: white;
  color: #4CAF50;
}

/* 하단 컨텐츠 영역 */
.main-content-section {
  background-color: #f7f7f7;
  flex-grow: 1;
  display: flex;
  justify-content: space-around;
  padding: 2rem;
  gap: 2rem;
}

/* 각 컨텐츠 영역 스타일 */
.chart-container,
.description-container,
.buttons-container {
  background-color: white;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  padding: 1.5rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.chart-container {
  flex: 2; /* 그래프 영역을 더 넓게 */
}

.description-container {
  flex: 1;
}

.buttons-container {
  flex: 1;
  gap: 1rem; /* 버튼 사이 간격 */
}

.description-container p {
  line-height: 1.6;
  color: #555;
}

.menu-button {
  width: 100%;
  padding: 0.75rem 1rem;
  border: none;
  border-radius: 20px;
  background-color: #2196F3;
  color: white;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.1s;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}

.menu-button:hover {
  background-color: #1a7bb9;
  transform: translateY(-2px);
}

/* 반응형 디자인 */
@media (max-width: 768px) {
  .main-content-section {
    flex-direction: column;
  }
}
</style>

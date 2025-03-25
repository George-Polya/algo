use std::io::{self, BufRead};

fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();

    // 첫 줄: N과 K 읽기
    let first_line = lines.next().unwrap().unwrap();
    let mut nums = first_line.split_whitespace();
    let n: usize = nums.next().unwrap().parse().unwrap();
    let k: i32 = nums.next().unwrap().parse().unwrap();

    // 두 번째 줄: 시험지 점수들
    let second_line = lines.next().unwrap().unwrap();
    let arr: Vec<i32> = second_line
        .split_whitespace()
        .map(|s| s.parse().unwrap())
        .collect();

    // 전체 점수 합 계산 (right의 초기값을 위해)
    let total: i32 = arr.iter().sum();

    // 이분 탐색 범위 설정
    let mut left: i32 = 0;
    let mut right: i32 = total + 1;
    let mut answer = 0;

    while left <= right {
        let mid = (left + right) / 2;
        let mut count = 0;
        let mut sum = 0;
        // 배열을 순회하며 mid 이상의 그룹을 몇 개 만들 수 있는지 카운트
        for &score in &arr {
            sum += score;
            if sum >= mid {
                count += 1;
                sum = 0;
            }
        }
        // 그룹의 개수가 k개 미만이면 기준(mid)이 너무 크므로 줄임
        if count < k {
            right = mid - 1;
        } else {
            answer = mid;
            left = mid + 1;
        }
    }

    println!("{}", answer);
}
package com.spring.pointcut;

public class BehaviorImpl implements Behavior{

	@Override
	public void 잠자기() {
		System.out.println("쿨쿨");
	}

	@Override
	public void 공부하기() {
		System.out.println("열공");
	}

	@Override
	public void 밥먹기() {
		System.out.println("냠냠");
	}

	@Override
	public void 데이트() {
		System.out.println("데이뚜");
	}

	@Override
	public void 운동() {
		System.out.println("으쌰");
	}

	@Override
	public void 놀기() {
		System.out.println("꺄");
	}

	@Override
	public void 정신수양() {
		System.out.println("....");
	}

}

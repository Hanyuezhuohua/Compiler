	.text
	.globl	g_init
	.p2align	1
	.type	g_init,@function
g_init:
.g_init_.0: 
	addi sp, sp, 0
	mv %0, s0
	mv %1, s1
	mv %2, s2
	mv %3, s3
	mv %4, s4
	mv %5, s5
	mv %6, s6
	mv %7, s7
	mv %8, s8
	mv %9, s9
	mv %10, s10
	mv %11, s11
	mv %12, ra
	j .g_init_.1
.g_init_.1: 
	li %13, 20
	mv a0, %13
	call malloc
	mv %14, a0
	mv %15, %14
	li %16, 4
	sw %16, 0(%15)
	addi %17, %15, 4
	mv %18, %17
	lui %19, %hi(a)
	sw %18, %lo(a)(%19)
	mv s0, %0
	mv s1, %1
	mv s2, %2
	mv s3, %3
	mv s4, %4
	mv s5, %5
	mv s6, %6
	mv s7, %7
	mv s8, %8
	mv s9, %9
	mv s10, %10
	mv s11, %11
	mv ra, %12
	addi sp, sp, 0
	ret

	.globl	main
	.p2align	1
	.type	main,@function
main:
.main_.0: 
	addi sp, sp, 0
	mv %20, s0
	mv %21, s1
	mv %22, s2
	mv %23, s3
	mv %24, s4
	mv %25, s5
	mv %26, s6
	mv %27, s7
	mv %28, s8
	mv %29, s9
	mv %30, s10
	mv %31, s11
	mv %32, ra
	j .main_.1
.main_.1: 
	call g_init
	li %33, 20
	mv a0, %33
	call malloc
	mv %34, a0
	mv %35, %34
	li %36, 4
	sw %36, 0(%35)
	addi %37, %35, 4
	mv %38, %37
	mv %39, %38
	mv %40, %39
	lw %41, a
	sw %41, 0(%40)
	addi %42, %39, 4
	mv %43, %42
	lw %44, a
	sw %44, 0(%43)
	addi %45, %39, 8
	mv %46, %45
	lw %47, a
	sw %47, 0(%46)
	addi %48, %39, 12
	mv %49, %48
	lw %50, a
	sw %50, 0(%49)
	mv %51, %39
	addi %52, %51, -4
	mv %53, %52
	lw %54, 0(%53)
	mv a0, %54
	call _gbl_toString
	mv %55, a0
	mv a0, %55
	call _gbl_println
	mv %56, zero
	j .main_.2
.main_.2: 
	mv %57, %39
	lw %58, 0(%57)
	addi %59, %58, -4
	mv %60, %59
	lw %61, 0(%60)
	blt %56, %61, .main_.16
	j .main_.3
.main_.3: 
	mv %68, zero
	j .main_.4
.main_.4: 
	addi %70, %39, 4
	mv %71, %70
	lw %72, 0(%71)
	addi %73, %72, -4
	mv %74, %73
	lw %75, 0(%74)
	blt %68, %75, .main_.14
	j .main_.5
.main_.5: 
	la %84, str0
	mv %85, %84
	mv a0, %85
	call _gbl_println
	mv %86, zero
	j .main_.6
.main_.6: 
	addi %88, %39, 8
	mv %89, %88
	lw %90, 0(%89)
	addi %91, %90, -4
	mv %92, %91
	lw %93, 0(%92)
	blt %86, %93, .main_.12
	j .main_.7
.main_.7: 
	mv %100, zero
	j .main_.8
.main_.8: 
	addi %102, %39, 12
	mv %103, %102
	lw %104, 0(%103)
	addi %105, %104, -4
	mv %106, %105
	lw %107, 0(%106)
	blt %100, %107, .main_.10
	j .main_.9
.main_.9: 
	mv a0, zero
	mv s0, %20
	mv s1, %21
	mv s2, %22
	mv s3, %23
	mv s4, %24
	mv s5, %25
	mv s6, %26
	mv s7, %27
	mv s8, %28
	mv s9, %29
	mv s10, %30
	mv s11, %31
	mv ra, %32
	addi sp, sp, 0
	ret
.main_.10: 
	addi %108, %39, 12
	mv %109, %108
	lw %110, 0(%109)
	slli %111, %100, 2
	add %112, %110, %111
	mv %113, %112
	lw %114, 0(%113)
	mv a0, %114
	call _gbl_toString
	mv %115, a0
	mv a0, %115
	call _gbl_print
	j .main_.11
.main_.11: 
	addi %116, %100, 1
	mv %100, %116
	j .main_.8
.main_.12: 
	addi %94, %39, 8
	mv %95, %94
	lw %96, 0(%95)
	slli %97, %86, 2
	add %98, %96, %97
	mv %99, %98
	sw zero, 0(%99)
	j .main_.13
.main_.13: 
	addi %101, %86, 1
	mv %86, %101
	j .main_.6
.main_.14: 
	addi %76, %39, 4
	mv %77, %76
	lw %78, 0(%77)
	slli %79, %68, 2
	add %80, %78, %79
	mv %81, %80
	lw %82, 0(%81)
	mv a0, %82
	call _gbl_toString
	mv %83, a0
	mv a0, %83
	call _gbl_print
	j .main_.15
.main_.15: 
	addi %87, %68, 1
	mv %68, %87
	j .main_.4
.main_.16: 
	mv %62, %39
	lw %63, 0(%62)
	slli %64, %56, 2
	add %65, %63, %64
	mv %66, %65
	call _gbl_getInt
	mv %67, a0
	sw %67, 0(%66)
	j .main_.17
.main_.17: 
	addi %69, %56, 1
	mv %56, %69
	j .main_.2

	.type	a,@object
	.section	.data
	.globl	a
	.p2align	2
a:
	.zero	4
	.size	a, 4

	.type	str0,@object
	.section	.rodata
str0:
	.asciz	""
	.size	str0, 1


#include <stdio.h>
#define MAX_STACK_SIZE 10000
#define MAX_STRING_SIZE 6

typedef struct Stack
{
	int head;
	int elements[MAX_STACK_SIZE];
} Stack;

int size(Stack* stack)
{
	return stack->head + 1;
}

int empty(Stack* stack)
{
    return !size(stack);
}

void push(Stack* stack, int num)
{
	stack->head++;
	stack->elements[stack->head] = num;
}

int pop(Stack* stack)
{
	if (empty(stack))
	{
		return -1;
	}
	stack->head--;
	return stack->elements[stack->head + 1];
}

int top(Stack* stack)
{
	return empty(stack) ? -1 : stack->elements[stack->head];
}

int main(void)
{
	int num;
	int test_case;
	char str[MAX_STRING_SIZE];
	Stack stack;

	stack.head = -1;
	scanf("%d", &test_case);
	for (int i = 0; i < test_case; i++)
	{
		scanf("%s", str);
		if (str[1] == 'u')
		{
			scanf("%d", &num);
			push(&stack, num);
		}
		else if (str[0] == 't')
		{
			printf("%d\n", top(&stack));
		}
		else if (str[0] == 's')
		{
			printf("%d\n", size(&stack));
		}
		else if (str[0] == 'e')
		{
			printf("%d\n", empty(&stack));
		}
		else
		{
			printf("%d\n", pop(&stack));
		}
	}
	return 0;
}